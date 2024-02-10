package strategy.impl;

import java.math.BigDecimal;

import dominio.EntidadeDominio;
import dominio.venda.EnderecoEntrega;
import dominio.venda.Frete;

public class CalcularFreteEntrega extends AbstractValidador {
//    private static final String URL_CORREIOS = "http://ws.correios.com.br/calculador/CalcPrecoPrazo.aspx";
//    private String cepDestino = "";
//    
    @Override
    public String processar(EntidadeDominio entidade) {
        EnderecoEntrega endereco = (EnderecoEntrega) entidade;

        Frete frete = Frete.builder()
                .endereco(endereco).build();
//        
//        calcularPrazoValor(frete);
//        

        frete.setValor(new BigDecimal("21.10"));
        frete.setPrazo(2);
        endereco.setFrete(frete);

        return null;
    }
//
//    private String gerarStringQuery() {
//        StringBuilder sb = new StringBuilder();
//        
//        String codEmpresa = "";
//        String senha = "";
//        Double diametro = 0.0;
//        String maoPropria = "";
//        Double valorDeclarado = 0.0;
//        String avisoRecebimento = "N";
//        Integer codFormato = 1;
//        String codServico = "40010";
//        String cepOrigem = "08696320";
//        
//        Double comprimento = 20d;
//        Double altura = 20d;
//        Double largura = 20d;
//        String peso = "1";
//        
//        sb.append("?StrRetorno=XML").append("&nIndicaCalculo=3");
//        sb.append("&nCdEmpresa=").append(codEmpresa);
//        sb.append("&sDsSenha=").append(senha);
//        sb.append("&nCdServico=").append(codServico);
//        sb.append("&sCepOrigem=").append(cepOrigem);
//        sb.append("&sCepDestino=").append(cepDestino);
//        sb.append("&nVlPeso=").append(peso);
//        sb.append("&nCdFormato=").append(codFormato);
//        sb.append("&nVlComprimento=").append(comprimento);
//        sb.append("&nVlAltura=").append(altura);
//        sb.append("&nVlLargura=").append(largura);
//        sb.append("&nVlDiametro=").append(diametro);
//        sb.append("&sCdMaoPropria=").append(maoPropria);
//        sb.append("&nVlValorDeclarado=").append(valorDeclarado);
//        sb.append("&sCdAvisoRecebimento=").append(avisoRecebimento);
//
//        return sb.toString();
//        
//    }
//    
//    private String realizarConsulta() {
//        String urlConsulta = URL_CORREIOS + gerarStringQuery();
//        
//        try {
//            InputStream inputStream = new URL(urlConsulta).openStream();
//
//            BufferedReader br = null;
//            StringBuilder sb = new StringBuilder();
//
//            String line;
//            try {
//
//                br = new BufferedReader(new InputStreamReader(inputStream));
//                while ((line = br.readLine()) != null) {
//                    sb.append(line);
//                }
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (br != null) {
//                    try {
//                        br.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            return sb.toString();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    
//    private void calcularPrazoValor(Frete frete) {
//        this.cepDestino = frete.getEndereco().getEndereco().getCep().replace(".", "").replace("-", "");
//        
//        String xml = realizarConsulta();
//        Document doc = Jsoup.parse(xml);
//        
//        Elements services = doc.select("cServico");
//
//        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance(new Locale("pt", "BR"));
//        
//        for (Element element : services) {
//        
//            frete.setPrazo(Integer.parseInt(element.select("PrazoEntrega").text()));
//        
//            try {
//                frete.setValor(new BigDecimal(
//                            df.parse(element.select("Valor").text()).toString()
//                        ));
//                
//            } catch (ParseException e) {
//                throw new RuntimeException(e);
//            }
//            
//        }
//
//    } 

}
