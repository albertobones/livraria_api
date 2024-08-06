package com.teste.livraria.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.teste.livraria.dto.AssuntoDTO;
import com.teste.livraria.dto.AutorDTO;
import com.teste.livraria.dto.LivroDTO;
import com.teste.livraria.dto.relatorio.LivroAutorAssuntoDTO;
import com.teste.livraria.repository.relatorio.RelatorioRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatorioService {
	
	@Autowired
    private RelatorioRepository relatorioRepository;
    
	public byte[] gerarRelatorio(String reportType) throws JRException, IOException {
        List<AutorDTO> autoresList = montarListaObjetos();
        return gerarPdf(reportType, autoresList);
    }
	
	public List<AutorDTO> montarListaObjetos() {
		List<LivroAutorAssuntoDTO> resultados = relatorioRepository.findAllLivroAutorAssunto();

        Map<Integer, AutorDTO> autoresMap = new LinkedHashMap<>();

        for (LivroAutorAssuntoDTO resultado : resultados) {
            AutorDTO autor = autoresMap.computeIfAbsent(resultado.getCodAu(), k -> {
                AutorDTO a = new AutorDTO();
                a.setCodAu(resultado.getCodAu());
                a.setNome(resultado.getNome());
                a.setLivroDTOs(new ArrayList<>());
                return a;
            });

            if (resultado.getCodL() != null) {
                LivroDTO livro = autor.getLivroDTOs().stream()
                        .filter(l -> l.getCodL().equals(resultado.getCodL()))
                        .findFirst()
                        .orElseGet(() -> {
                            LivroDTO l = new LivroDTO();
                            l.setCodL(resultado.getCodL());
                            l.setTitulo(resultado.getTitulo());
                            l.setEditora(resultado.getEditora());
                            l.setAnoPublicacao(resultado.getAnoPublicacao());
                            l.setEdicao(resultado.getEdicao());
                            l.setValor(resultado.getValor());
                            l.setAssuntos(new ArrayList<>());
                            autor.getLivroDTOs().add(l);
                            return l;
                        });

                if (resultado.getCodAs() != null) {
                    AssuntoDTO assunto = new AssuntoDTO();
                    assunto.setCodAs(resultado.getCodAs());
                    assunto.setDescricao(resultado.getDescricao());
                    livro.getAssuntos().add(assunto);
                }
            }
        }

        return new ArrayList<>(autoresMap.values());
	}
	
	public byte[] gerarPdf(String reportType, List<AutorDTO> autoresList) throws JRException, IOException {
		// Defina o parâmetro para o diretório dos sub-relatórios
        String subReportDir = "./reports/";
        // Parâmetros do relatório
        Map<String, Object> parameters = new HashMap<>();
        
        parameters.put("SUBREPORT_DIR", subReportDir);
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(autoresList);
        InputStream reportStream = new ClassPathResource("reports/" + reportType + ".jrxml").getInputStream();
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
	}

}
