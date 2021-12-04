package com.example.aplicacionsemestral.objetos;

public class NoticiaVo {
    private String id_noticia;
    private String info;
    private String foto;

    public NoticiaVo(String id_noticia, String info, String foto) {
        this.id_noticia = id_noticia;
        this.info = info;
        this.foto = foto;
    }
    public NoticiaVo(){
    }

    public String getId_noticia() {
        return id_noticia;
    }

    public void setId_noticia(String id_noticia) {
        this.id_noticia = id_noticia;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
