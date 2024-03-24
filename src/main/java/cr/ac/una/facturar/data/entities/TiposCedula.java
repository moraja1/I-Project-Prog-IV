package cr.ac.una.facturar.data.entities;

public enum TiposCedula {
    Fisica("Fisica"),
    Juridica("Juridica");
    private String name;
    TiposCedula(String name) {
        this.name = name;
    }
}
