package _AAInterviews.Google;
import org.junit.Test;

import java.util.*;
public class _2115_Find_All_Possible_Recipes_from_Given_Supplies {
    // using in-degree
    public List<String> findAllRecipes_1(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        List<String> res = new ArrayList<>();
        Map<String, List<String>> ingredientsPathMap = new HashMap<>();
        Map<String, List<String>> recipesMap = new HashMap<>();
        Map<String, Integer> degreeMap = new HashMap<>();

        for(int i=0; i<recipes.length; i++) {
            List<String> list = ingredients.get(i);
            recipesMap.put(recipes[i], list);
            degreeMap.put(recipes[i], list.size());
            for(int j=0; j<list.size(); j++) {
                if(!ingredientsPathMap.containsKey(list.get(j))) ingredientsPathMap.put(list.get(j), new ArrayList<>());
                ingredientsPathMap.get(list.get(j)).add(recipes[i]);
            }
        }

        Queue<String> queue = new LinkedList<>();
        Set<String> created = new HashSet<>();
        for(int i=0; i<supplies.length; i++) {
            if(recipesMap.containsKey(supplies[i])) res.add(supplies[i]);
            queue.add(supplies[i]);
            degreeMap.put(supplies[i],0);
            created.add(supplies[i]);
        }
        while(!queue.isEmpty()) {
            String curSupply = queue.poll();
            if(!ingredientsPathMap.containsKey(curSupply)) {
                continue;
            }
            List<String> nexts = ingredientsPathMap.get(curSupply);
            for(String next:nexts) {
                if(!created.contains(next)) {
                    degreeMap.put(next, degreeMap.get(next)-1);
                    if(degreeMap.get(next)==0) {
                        queue.add(next);
                        res.add(next);
                        created.add(next);
                    }
                }
            }
        }
        return res;

    }


    // use hash table
    Map<String, List<String>> recipeMap;
    Map<String, Boolean> canCreateMap;
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        canCreateMap = new HashMap<>();
        for(String supply:supplies) canCreateMap.put(supply, true);
        recipeMap = new HashMap<>();
        for(int i=0; i<recipes.length; i++) {
            recipeMap.put(recipes[i], ingredients.get(i));
        }
        List<String> res = new ArrayList<>();

        for(int i=0; i<recipes.length; i++) {
            if(canCreate(recipes[i], new HashSet<>())) res.add(recipes[i]);
        }
        return res;
    }

    private boolean canCreate(String toCreate, Set<String> requested) {
        if(requested.contains(toCreate)) return false; // loop
        if(canCreateMap.containsKey(toCreate)) return canCreateMap.get(toCreate);
        if(!recipeMap.containsKey(toCreate)) return false;
        List<String> ingredients = recipeMap.get(toCreate);
        requested.add(toCreate);
        for(String ingredient:ingredients) {
            if(canCreate(ingredient, requested)) continue;
            else {
//                canCreateMap.put(toCreate, false);
                return false;
            }
        }
        canCreateMap.put(toCreate, true);
        return true;
    }

    @Test
    public void test(){
        String[] recipes = {"loknei","ecjxt","tkzmelkn","atsvbrx","ncacfq","i","gwge","kjubpqzpz","lmfrvhjspq","wa","qmy","jezfzmkqe","kxlzfahxl","cnwtnx","aatvoxxjyj","hdynuzjui","xe","tecbdw","fqerls","ravn","zzdy","rbzddbxem","vlbke","blfqqkvsm","ogoyp","irblznb","nkn","inbkag","zqgxptohzb"};

    }

}
