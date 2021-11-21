package com.example.aplicacionsemestral.objetos;

public class NoticiaVo {
    private String info;
    private int foto;

    public NoticiaVo(){

    }

    public NoticiaVo(String info, int foto) {
        this.info = info;
        this.foto = foto;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
