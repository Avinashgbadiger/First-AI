package com.ai;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


@RestController
public class GenAIController {

    @Autowired
    ChatService chatService;

    @Autowired
    ImageService imageService;

    private final RecipeService recipeService;

    public GenAIController(ChatService chatService, RecipeService recipeService) {
        this.chatService = chatService;
        this.recipeService = recipeService;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }

    @GetMapping("ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt) {

        return chatService.getResponse(prompt);
    }

    // Single - Image Generation
//    @GetMapping("generate-image")
//    public void generateImage(HttpServletResponse response, @RequestParam String prompt) throws IOException {
//        ImageResponse imageResponse = this.imageService.generateImage(prompt);
//        String url = imageResponse.getResult().getOutput().getUrl();
//        response.sendRedirect(url);
//    }
    @GetMapping("generate-image")
    public List<String> generateImage(HttpServletResponse response,
                                      @RequestParam String prompt,
                                      @RequestParam(defaultValue = "hd") String quality,
                                      @RequestParam(defaultValue = "1") int n,
                                      @RequestParam(defaultValue = "1024") int width,
                                      @RequestParam(defaultValue = "1024") int height
    ) throws IOException {
        ImageResponse imageResponse = this.imageService.generateImage(prompt, quality, n, width, height);
        // Steams to get URLs from ImageResponse

        return imageResponse.getResults().stream()
                .map(result -> result.getOutput().getUrl())
                .toList();
    }


    @GetMapping("recipe-creator")
    public String recipeCreator(@RequestParam String ingredients,
                                @RequestParam(defaultValue = "any") String cuisine,
                                @RequestParam(defaultValue = "") String dietaryRestriction) {

        return recipeService.createRecipe(ingredients, cuisine, dietaryRestriction);
    }


}
