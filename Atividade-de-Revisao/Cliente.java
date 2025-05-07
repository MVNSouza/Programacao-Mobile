public class Cliente {
    private String nome;
    private String email;
    private Double saldoCarteira;

    public Cliente(String nome, String email, Double saldoCarteira){
        this.nome = nome;
        this.email = email;
        this.saldoCarteira = saldoCarteira;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (!nome.isBlank()){
            this.nome = nome;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!email.isBlank()){
            this.email = email;
        }
    }

    public Double getSaldoCarteira() {
        return saldoCarteira;
    }

    public void setSaldoCarteira(Double saldoCarteira) {
        if (saldoCarteira > 0){
            this.saldoCarteira = saldoCarteira;
        } else {
            this.saldoCarteira = 0.0;
        }
    }
}
