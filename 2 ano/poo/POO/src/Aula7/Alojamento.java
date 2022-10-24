package Aula7;

public class Alojamento {
    private String codigo;
    private String nome;
    private String local;
    private double preco;
    private boolean disponivel;
    private double avaliacao;

    public Alojamento(String codigo, String nome, String local, double preco, double avaliacao){
        if(avaliacao >= 1 && avaliacao <= 5){
            this.codigo = codigo;
            this.nome = nome;
            this.local = local;
            this.preco = preco;
            this.disponivel = true;
            this.avaliacao = avaliacao;
        }
    }

    public boolean checkIn(){
        if(disponivel){
            disponivel = false;
            return true;
        }
        return false;
    }

    public boolean checkOut(){
        if(!disponivel){
            disponivel = true;
            return true;
        }
        return false;
    }
    
    
    @Override
    public String toString() {
        return "Alojamento [avaliacao=" + avaliacao + ", codigo=" + codigo + ", disponivel=" + disponivel + ", local="
        + local + ", nome=" + nome + ", precoNoite=" + preco + "] ";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(avaliacao);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + (disponivel ? 1231 : 1237);
        result = prime * result + ((local == null) ? 0 : local.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        temp = Double.doubleToLongBits(preco);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Alojamento other = (Alojamento) obj;
        if (Double.doubleToLongBits(avaliacao) != Double.doubleToLongBits(other.avaliacao))
            return false;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        if (disponivel != other.disponivel)
            return false;
        if (local == null) {
            if (other.local != null)
                return false;
        } else if (!local.equals(other.local))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (Double.doubleToLongBits(preco) != Double.doubleToLongBits(other.preco))
            return false;
        return true;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public double getPrecoNoite() {
        return preco;
    }

    public void setPrecoNoite(double precoNoite) {
        this.preco = precoNoite;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(double avaliacao) {
        this.avaliacao = avaliacao;
    }
}
