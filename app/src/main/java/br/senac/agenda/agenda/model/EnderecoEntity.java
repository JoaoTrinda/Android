package br.senac.agenda.agenda.model;

public class EnderecoEntity {
    private Integer id;
    private String rua;
    private String numero;
    private String cidade;

    public EnderecoEntity(String rua, String numero, String cidade) {
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
    }

    public EnderecoEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRua() { return rua; }

    public void setRua(String rua) { this.rua = rua; }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "rua='" + rua + '\'' +
                ", numero='" + numero + '\'' +
                ", cidade='" + cidade;
    }
}