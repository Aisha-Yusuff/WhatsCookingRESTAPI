package com.aishayusuff.RecipeApi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Override
    public List<Recipe> getAllRecipes() {
        return new ArrayList<>();
    }

}
