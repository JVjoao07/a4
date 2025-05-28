package com.nutrifacil.model;

import java.util.HashSet;
import java.util.Set;

public class UserProfile {
    private double weight; // em kg
    private double height; // em cm
    private int age;
    private String gender; // "Masculino" ou "Feminino"
    private DietType selectedDiet;
    private String objective; // "Emagrecimento" ou "Hipertrofia"
    private Set<String> allergies;
    private Set<String> foodPreferences;

    public UserProfile() {
        this.allergies = new HashSet<>();
        this.foodPreferences = new HashSet<>();
    }

    // Getters e Setters
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight <= 0) {
            throw new IllegalArgumentException("Peso deve ser maior que zero");
        }
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Altura deve ser maior que zero");
        }
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Idade deve ser maior que zero");
        }
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (!gender.equals("Masculino") && !gender.equals("Feminino")) {
            throw new IllegalArgumentException("Gênero deve ser 'Masculino' ou 'Feminino'");
        }
        this.gender = gender;
    }

    public DietType getSelectedDiet() {
        return selectedDiet;
    }

    public void setSelectedDiet(DietType selectedDiet) {
        this.selectedDiet = selectedDiet;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        if (!objective.equals("Emagrecimento") && !objective.equals("Hipertrofia")) {
            throw new IllegalArgumentException("Objetivo deve ser 'Emagrecimento' ou 'Hipertrofia'");
        }
        this.objective = objective;
    }

    public Set<String> getAllergies() {
        return new HashSet<>(allergies);
    }

    public void addAllergy(String allergy) {
        this.allergies.add(allergy);
    }

    public void removeAllergy(String allergy) {
        this.allergies.remove(allergy);
    }

    public Set<String> getFoodPreferences() {
        return new HashSet<>(foodPreferences);
    }

    public void addFoodPreference(String preference) {
        this.foodPreferences.add(preference);
    }

    public void removeFoodPreference(String preference) {
        this.foodPreferences.remove(preference);
    }

    // Método para calcular o IMC
    public double calculateBMI() {
        double heightInMeters = height / 100;
        return weight / (heightInMeters * heightInMeters);
    }

    // Método para calcular a TMB usando a fórmula de Mifflin
    public double calculateBMR() {
        if (gender.equals("Masculino")) {
            return (10 * weight) + (6.25 * height) - (5 * age) + 5;
        } else {
            return (10 * weight) + (6.25 * height) - (5 * age) - 161;
        }
    }

    // Método para calcular o consumo diário de água
    public double calculateWaterIntake() {
        return 35 * weight; // 35ml por kg de peso
    }

    // Método para obter a classificação do IMC
    public String getBMICategory() {
        double bmi = calculateBMI();
        if (bmi < 18.5) {
            return "Abaixo do peso";
        } else if (bmi < 25) {
            return "Peso normal";
        } else if (bmi < 30) {
            return "Sobrepeso";
        } else {
            return "Obesidade";
        }
    }
} 