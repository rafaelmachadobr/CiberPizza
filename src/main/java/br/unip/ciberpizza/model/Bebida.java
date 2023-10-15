    package br.unip.ciberpizza.model;

    import jakarta.persistence.DiscriminatorValue;
    import jakarta.persistence.Entity;
    import lombok.Data;
    import lombok.EqualsAndHashCode;

    @Entity
    @Data
    @EqualsAndHashCode(callSuper = false)
    @DiscriminatorValue("Bebida")
    public class Bebida extends Produto {
    }
