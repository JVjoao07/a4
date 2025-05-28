package com.nutrifacil.model;

public enum DietType {
    MEDITERRANEA("Mediterrânea", "Dieta rica em azeite de oliva, peixes, grãos integrais, legumes e frutas. Foco em saúde cardiovascular."),
    LOW_CARB("Low Carb", "Dieta com redução de carboidratos, aumento de proteínas e gorduras boas. Foco em emagrecimento."),
    CETOGENICA("Cetogênica", "Dieta com ingestão muito baixa de carboidratos e alta em gorduras. Para perda de gordura rápida."),
    VEGETARIANA("Vegetariana", "Dieta sem carnes, incluindo ovos, laticínios, grãos, vegetais e leguminosas.");

    private final String name;
    private final String description;

    DietType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name;
    }
} 