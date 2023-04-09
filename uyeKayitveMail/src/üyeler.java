public class üyeler {           //ÜYELER ÜST SINIFI    TÜM ÜYE TİPLERİNİN SAHİP OLDUĞU ORTAK ÖZELLİKLER
    public String name;
    public String surname;
    public String email;

    public üyeler(String name, String surname, String email) {
        this.setName(name);
        this.setSurname(surname);
        this.setEmail(email);
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void userInfo(){
        System.out.println("Kullanıcı Bilgileri");
        System.out.println("İsim: " +this.name);
        System.out.println("Soyisim: " +this.surname);
        System.out.println("Email: " +this.email);
    }


}
