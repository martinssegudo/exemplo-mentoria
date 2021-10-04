package br.com.mentoria.adaptadores;

import br.com.mentoria.apis.entidades.CampeaoAPI;
import br.com.mentoria.bd.entidades.CampeaoEntidade;
import br.com.mentoria.servicos.entidades.Campeao;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CapeaoServiceAdpter {

    private Campeao campeao;
    private List<Campeao> campeoes;

    public CapeaoServiceAdpter(CampeaoAPI campeaoAPI){
        this.campeao = convertCampeaoDTOEmCampeao(campeaoAPI);
    }

    public CapeaoServiceAdpter(CampeaoEntidade campeaoEntidade){
        this.campeao = convertCampeaoEntityEmCampeao(campeaoEntidade);
    }

    public CapeaoServiceAdpter(List<CampeaoEntidade> campeaoEntities){
        campeoes = convertListEntityEmListCampeao(campeaoEntities);
    }


    private List<Campeao> convertListEntityEmListCampeao(List<CampeaoEntidade> campeaoEntidadeList){
        campeoes = new ArrayList<>();
        for (CampeaoEntidade campeaoEntidade : campeaoEntidadeList) {
            campeoes.add(convertCampeaoEntityEmCampeao(campeaoEntidade));
        }
        return campeoes;
    }

    private Campeao convertCampeaoDTOEmCampeao(CampeaoAPI campeaoAPI){
        return Campeao.builder()
                .email(campeaoAPI.getEmail())
                .nome(campeaoAPI.getNome())
                .corSabre(campeaoAPI.getCorSabre())
                .tipo(new TipoCampeaoAdapter(campeaoAPI.getTipoCapeao()).getTipoCampeao())
                .build();
    }

    private Campeao convertCampeaoEntityEmCampeao(CampeaoEntidade campeaoEntidade){
        return Campeao.builder()
                .hp(campeaoEntidade.getHp())
                .afinadadeForca(campeaoEntidade.getAfinadadeForca())
                .previsao(campeaoEntidade.getPrevisao())
                .mental(campeaoEntidade.getMental())
                .corSabre(campeaoEntidade.getCorSabre())
                .nome(campeaoEntidade.getNome())
                .forcaFisica(campeaoEntidade.getForcaFisica())
                .email(campeaoEntidade.getEmail())
                .habilidadeComSabre(campeaoEntidade.getHabilidadeComSabre())
                .tipo(new TipoCampeaoAdapter(campeaoEntidade.getTipo()).getTipoCampeao())
                .build();
    }
}
