package com.example.nutrisend.platform.order.domain.valueObjects;

public enum Meal {
    // Desayunos
    OATMEAL(1, "Oatmeal with Milk, Egg Whites and Banana", 5, MealCategory.BREAKFAST),
    GREEK_YOGURT(2, "Greek Yogurt Bowl with Granola and Nuts", 6, MealCategory.BREAKFAST),
    PEANUT_BUTTER_TOAST(3, "Peanut Butter and Banana Toast", 4, MealCategory.BREAKFAST),
    TOAST_CHEESE_SPINACH(4, "Toast with Cheese and Spinach", 5, MealCategory.BREAKFAST),
    EGG_TOAST(5, "Egg with Toast and Avocado", 7, MealCategory.BREAKFAST),
    TOAST_SCRAMBLED_EGGS(6, "Toast with Scrambled Eggs and Spinach", 6, MealCategory.BREAKFAST),
    OATMEAL_BLUEBERRIES(7, "Oatmeal with Blueberries and Almonds", 5, MealCategory.BREAKFAST),

    // Almuerzos
    CHICKEN_RICE_BOWL(8, "Chicken Rice Bowl", 8, MealCategory.LUNCH),
    PORK_MASH_GREEN_SALAD(9, "Pork with Mash and Green Salad", 9, MealCategory.LUNCH),
    CAESAR_SALAD_CHICKEN(10, "Caesar Salad with Chicken", 7, MealCategory.LUNCH),
    MEAT_BEANS_VEGETABLES_BURRITO(11, "Meat, Beans and Vegetables Burrito", 10, MealCategory.LUNCH),
    GRILLED_SALMON_ASPARAGUS(12, "Grilled Salmon with Steamed Asparagus", 12, MealCategory.LUNCH),
    CHICKPEA_SALAD_TUNA(13, "Fresh Chickpea Salad with Tuna", 8, MealCategory.LUNCH),
    MEAT_STEW_POTATO_CARROT(14, "Meat Stew with Potato and Carrot", 10, MealCategory.LUNCH),

    // Cenas
    SALMON_ASPARAGUS_POTATO(15, "Salmon with Asparagus and Potato", 11, MealCategory.DINNER),
    GRILLED_SALMON_MASHED_POTATO(16, "Grilled Salmon with Mashed Sweet Potato and Asparagus", 12, MealCategory.DINNER),
    MEAL_STEW_POTATO_CARROT_1(17, "Meal Stew with Potato and Carrot", 9, MealCategory.DINNER),
    MEAT_STEW_POTATO_CARROT_2(18, "Meat Stew with Potato and Carrot", 10, MealCategory.DINNER),
    SPINACH_CREPE_TURKEY_HAM(19, "Spinach Crepe with Turkey Ham", 8, MealCategory.DINNER),
    CHICKEN_CURRY_BASMATI_RICE(20, "Chicken Curry with Basmati Rice and Carrots", 10, MealCategory.DINNER);

    private final int id;
    private final String name;
    private final double price;
    private final MealCategory category;

    Meal(int id, String name, double price, MealCategory category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public MealCategory getCategory() {
        return category;
    }

    public static Meal fromId(int id) {
        for (Meal meal : values()) {
            if (meal.getId() == id) {
                return meal;
            }
        }
        throw new IllegalArgumentException("No meal found with ID: " + id);
    }
}