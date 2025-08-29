package br.com.service.http;

import br.com.domain.Agencia;
import br.com.domain.http.AgenciaHttp;
import br.com.domain.http.SituacaoCadastral;
import br.com.exceptions.AgenciaNaoAtivaOuEncontradaException;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AgenciaService {

    @RestClient
    private SituacaoCadastralHttpService situacaoCadastralHttpService;

    private List<Agencia> agencias = new ArrayList<>();

    public void cadastrar(Agencia agencia) {
        AgenciaHttp agenciaHttp =
                situacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());

        if (agenciaHttp.getSituacaoCadastral().equals(SituacaoCadastral.ATIVO)){
            agencias.add(agencia);
        } else {
            throw new AgenciaNaoAtivaOuEncontradaException();
        }
    }

}
