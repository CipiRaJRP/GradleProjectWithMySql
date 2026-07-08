package com.example.Selenium.tests;

import com.example.Selenium.pages.CatalogPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static java.lang.reflect.Modifier.*;
import static org.junit.jupiter.api.Assertions.*;
public class RefactoringTest {
    @Test
    @DisplayName("Refracting Test case")
    void refractoringTheExistingProject(){
          List<Field> catalogLocators = Arrays.stream(CatalogPage.class.getDeclaredFields())
                  .filter(field -> field.getType().equals(By.class))
                  .toList();
          List<Integer> catalogModifiers = catalogLocators.stream()
                  .map(modifier -> modifier.getModifiers())
                  .toList();
      //This is Field Modifier Assertion
          assertAll(
                  ()->assertTrue(catalogModifiers.stream()
                          .allMatch(Private-> isPrivate(Private))),

                  ()-> assertTrue(catalogModifiers.stream()
                          .allMatch(Static -> isStatic(Static))),

                  ()-> assertTrue(catalogModifiers.stream()
                          .allMatch(Final ->isFinal(Final))));

         List<Method> catalogMethodModifiers = Arrays.stream(CatalogPage.class.getMethods())
                 .filter(method -> method.getReturnType().equals(CatalogPage.class))
                 .toList();
// This is Method Modifiers Assertion
        for(Method method:catalogMethodModifiers){
            System.out.println(method);
        }
        assertAll(
                ()->assertTrue(catalogMethodModifiers.stream()
                        .allMatch(Public-> isPublic(Public.getModifiers()))),

                ()->assertTrue(catalogMethodModifiers.stream()
                        .allMatch(returnType ->returnType.getReturnType().equals(CatalogPage.class)))
        );
    }
}
