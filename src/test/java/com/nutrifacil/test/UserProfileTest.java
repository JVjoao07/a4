package com.nutrifacil.test;

import com.nutrifacil.model.DietType;
import com.nutrifacil.model.UserProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {
    private UserProfile profile;

    @BeforeEach
    void setUp() {
        profile = new UserProfile();
        profile.setWeight(70.0);
        profile.setHeight(170.0);
        profile.setAge(30);
        profile.setGender("Masculino");
        profile.setSelectedDiet(DietType.MEDITERRANEA);
        profile.setObjective("Emagrecimento");
    }

    @Test
    void testValidProfileCreation() {
        assertEquals(70.0, profile.getWeight());
        assertEquals(170.0, profile.getHeight());
        assertEquals(30, profile.getAge());
        assertEquals("Masculino", profile.getGender());
        assertEquals(DietType.MEDITERRANEA, profile.getSelectedDiet());
        assertEquals("Emagrecimento", profile.getObjective());
    }

    @Test
    void testInvalidWeight() {
        assertThrows(IllegalArgumentException.class, () -> profile.setWeight(-1.0));
        assertThrows(IllegalArgumentException.class, () -> profile.setWeight(0.0));
    }

    @Test
    void testInvalidHeight() {
        assertThrows(IllegalArgumentException.class, () -> profile.setHeight(-1.0));
        assertThrows(IllegalArgumentException.class, () -> profile.setHeight(0.0));
    }

    @Test
    void testInvalidAge() {
        assertThrows(IllegalArgumentException.class, () -> profile.setAge(-1));
        assertThrows(IllegalArgumentException.class, () -> profile.setAge(0));
    }

    @Test
    void testInvalidGender() {
        assertThrows(IllegalArgumentException.class, () -> profile.setGender("Outro"));
    }

    @Test
    void testInvalidObjective() {
        assertThrows(IllegalArgumentException.class, () -> profile.setObjective("Outro"));
    }

    @Test
    void testBMICalculation() {
        // IMC = peso / (altura/100)²
        // 70 / (170/100)² = 70 / 2.89 = 24.22
        assertEquals(24.22, profile.calculateBMI(), 0.01);
        assertEquals("Peso normal", profile.getBMICategory());
    }

    @Test
    void testBMRCalculation() {
        // TMB = (10 × peso) + (6,25 × altura) – (5 × idade) + 5
        // (10 × 70) + (6,25 × 170) – (5 × 30) + 5
        // 700 + 1062.5 - 150 + 5 = 1617.5
        assertEquals(1617.5, profile.calculateBMR(), 0.01);
    }

    @Test
    void testWaterIntakeCalculation() {
        // 35ml × peso
        // 35 × 70 = 2450ml
        assertEquals(2450.0, profile.calculateWaterIntake(), 0.01);
    }

    @Test
    void testAllergiesManagement() {
        profile.addAllergy("Lactose");
        profile.addAllergy("Glúten");
        assertTrue(profile.getAllergies().contains("Lactose"));
        assertTrue(profile.getAllergies().contains("Glúten"));
        
        profile.removeAllergy("Lactose");
        assertFalse(profile.getAllergies().contains("Lactose"));
        assertTrue(profile.getAllergies().contains("Glúten"));
    }

    @Test
    void testFoodPreferencesManagement() {
        profile.addFoodPreference("Frango");
        profile.addFoodPreference("Arroz integral");
        assertTrue(profile.getFoodPreferences().contains("Frango"));
        assertTrue(profile.getFoodPreferences().contains("Arroz integral"));
        
        profile.removeFoodPreference("Frango");
        assertFalse(profile.getFoodPreferences().contains("Frango"));
        assertTrue(profile.getFoodPreferences().contains("Arroz integral"));
    }
} 