public class Contacto {
  private String nome;
  private String telefone;
  private String email;

  String nome(){
    return nome.toUpperCase();
  }
  
  String telefone(){
      return telefone;
  }

  String eMail(){
      return email;
  }

  public Contacto(String nome, String telefone, String email){
    this.nome = nome;
    this.telefone = telefone;
    this.email = email;
  }

  Contacto(String nome, String telefone){
    this.nome = nome;
    this.telefone = telefone;
  }


}
