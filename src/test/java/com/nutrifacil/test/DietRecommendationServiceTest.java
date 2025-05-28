package com.nutrifacil.test;

import com.nutrifacil.model.DietType;
import com.nutrifacil.model.UserProfile;
import com.nutrifacil.service.DietRecommendationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

class DietRecommendationServiceTest {
    private DietRecommendationService service;
    private UserProfile profile;

    @BeforeEach
    void setUp() {
        service = new DietRecommendationService();
        profile = new UserProfile();
        profile.setWeight(70.0);
        profile.setHeight(170.0);
        profile.setAge(30);
        profile.setGender("Masculino");
    }

    @Test
    void testMediterraneanDietRecommendations() {
        profile.setSelectedDiet(DietType.MEDITERRANEA);
        profile.setObjective("Emagrecimento");
        
        Map<String, List<String>> recommendations = service.getRecommendations(profile);
        
        assertTrue(recommendations.containsKey("proteinas"));
        assertTrue(recommendations.containsKey("carboidratos"));
        assertTrue(recommendations.containsKey("legumes"));
        assertTrue(recommendations.containsKey("verduras"));
        
        List<String> proteins = recommendations.get("proteinas");
        assertTrue(proteins.contains("Peixe (salmão, atum, sardinha)"));
        assertTrue(proteins.contains("Frango grelhado"));
    }

    @Test
    void testLowCarbDietRecommendations() {
        profile.setSelectedDiet(DietType.LOW_CARB);
        profile.setObjective("Emagrecimento");
        
        Map<String, List<String>> recommendations = service.getRecommendations(profile);
        
        List<String> carbs = recommendations.get("carboidratos");
        assertTrue(carbs.stream().anyMatch(food -> food.contains("pequena porção")));
    }

    @Test
    void testKetogenicDietRecommendations() {
        profile.setSelectedDiet(DietType.CETOGENICA);
        profile.setObjective("Emagrecimento");
        
        Map<String, List<String>> recommendations = service.getRecommendations(profile);
        
        List<String> carbs = recommendations.get("carboidratos");
        assertTrue(carbs.stream().anyMatch(food -> food.contains("quantidade limitada")));
    }

    @Test
    void testVegetarianDietRecommendations() {
        profile.setSelectedDiet(DietType.VEGETARIANA);
        profile.setObjective("Hipertrofia");
        
        Map<String, List<String>> recommendations = service.getRecommendations(profile);
        
        List<String> proteins = recommendations.get("proteinas");
        assertTrue(proteins.contains("Tofu"));
        assertTrue(proteins.contains("Tempeh"));
        assertTrue(proteins.contains("Seitan"));
    }

    @Test
    void testAllergyFiltering() {
        profile.setSelectedDiet(DietType.MEDITERRANEA);
        profile.addAllergy("Peixe");
        
        Map<String, List<String>> recommendations = service.getRecommendations(profile);
        List<String> proteins = recommendations.get("proteinas");
        
        assertFalse(proteins.stream().anyMatch(food -> food.contains("Peixe")));
    }

    @Test
    void testWeightLossAdjustments() {
        profile.setSelectedDiet(DietType.MEDITERRANEA);
        profile.setObjective("Emagrecimento");
        
        Map<String, List<String>> recommendations = service.getRecommendations(profile);
        List<String> carbs = recommendations.get("carboidratos");
        
        assertFalse(carbs.stream().anyMatch(food -> food.contains("batata")));
        assertFalse(carbs.stream().anyMatch(food -> food.contains("arroz")));
    }

    @Test
    void testMuscleGainAdjustments() {
        profile.setSelectedDiet(DietType.MEDITERRANEA);
        profile.setObjective("Hipertrofia");
        
        Map<String, List<String>> recommendations = service.getRecommendations(profile);
        List<String> proteins = recommendations.get("proteinas");
        
        assertTrue(proteins.contains("Whey protein (suplemento)"));
        assertTrue(proteins.contains("Peito de frango (porção maior)"));
    }

    @Test
    void testDietSummary() {
        profile.setSelectedDiet(DietType.MEDITERRANEA);
        profile.setObjective("Emagrecimento");
        
        String summary = service.getDietSummary(profile);
        
        assertTrue(summary.contains("Resumo da Dieta Mediterrânea"));
        assertTrue(summary.contains("Taxa Metabólica Basal (TMB)"));
        assertTrue(summary.contains("IMC"));
        assertTrue(summary.contains("Consumo Diário de Água Recomendado"));
        assertTrue(summary.contains("Recomendações Alimentares"));
    }
} 