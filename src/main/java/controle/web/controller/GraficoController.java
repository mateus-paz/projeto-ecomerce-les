package controle.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import dao.impl.PedidoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/adm-grafico"})
public class GraficoController extends HttpServlet{
    private static final long serialVersionUID = 1L;
    
    protected void service(HttpServletRequest request, HttpServletResponse response){
        
        String dtInicio = request.getParameter("dtInicio");
        String dtFim = request.getParameter("dtFim");
        
        Map<String, Map<String, Integer> > dados = new TreeMap<>();
        if(dtInicio != null && dtFim != null) {
            PedidoDAO pedidoDao = new PedidoDAO();
            dados = pedidoDao.consultarNumeroVendas(dtInicio, dtFim);
        }
        
        String txtLabels = "[ ";
        String dataSet = "[";
        
        for(String data : dados.keySet()) {
            txtLabels += "'"+data+"', ";
        }
        txtLabels += "] ";
        List<String> categorias = new ArrayList<>();
        
        categorias.add("Aventura");
        categorias.add("Fantasia");
        categorias.add("Ficção");
        categorias.add("Infantojuvenil");
        categorias.add("Literatura Estrangeira");
        categorias.add("Romance");
        categorias.add("Suspense e Mistério");
        categorias.add("Erótico");
        
        for(String categoria : categorias) {
            String auxDataset = "{ label: '"+categoria+"', data: [";
            for( Map<String, Integer> mapa : dados.values()) {
                
                if(mapa.get(categoria) == null) {
                    auxDataset += 0+",";
                }else {
                    auxDataset += mapa.get(categoria)+",";
                }
                
            }
            auxDataset += "], borderWidth: 1}, ";
            dataSet += auxDataset;
        }
        
        dataSet += "]";

        request.getSession().setAttribute("dataSet", dataSet);
        request.getSession().setAttribute("txtLabels", txtLabels);
        
        
        RequestDispatcher rd = request.getRequestDispatcher("/adm_dashboard.jsp");
        
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
        
    }

}
