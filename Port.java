import java.util.Arrays;
import java.util.HashMap;

//Vodafone,STOCK,10|Google,STOCK,15:Vodafone,STOCK,15|Vodafone,BOND,10|Google,STOCK,10
//Vodafone,STOCK,10|Google,STOCK,15|Microsoft,BOND,15:Vodafone,STOCK,15|Google,STOCK,10|Microsoft,BOND,15

class Port{
    public static void main(String[] args) {
        String input = "Vodafone,STOCK,10|Google,STOCK,15:Vodafone,STOCK,15|Vodafone,BOND,10|Google,STOCK,10";
        matchBenchmark(input);
    }

    public static void matchBenchmark(String input){
        if(input.length() == 0){
            System.out.println("");
            return;
        }

        String[] portBench = input.split(":");
        String portfolio = portBench[0];
        String bench = portBench[1];
        // System.out.println(portfolio);
        // System.out.println(bench);
        String[] portAssets = portfolio.split("\\|");
        int portlen = portAssets.length;
        String[] benchAssets = bench.split("\\|");
        int benchlen = benchAssets.length;
        HashMap<String, HashMap<String, Integer>> assetMap = new HashMap<>();
        for(String asset: portAssets){
            String[] comp = asset.split(",");
            //comp[0] - Company name
            //comp[1] - Type of asset
            //comp[2] - Quantity
            String compName = comp[0];
            String aType = comp[1];

            //what if comp2 is empty
            int qty = Integer.parseInt(comp[2]);

            //check if the portfolio has the company entry
            if(!assetMap.containsKey(compName)){
                //create a new map to put inside the assetmap
                HashMap<String, Integer> typeMap = new HashMap<>(4);
                typeMap.put(aType, qty);
                assetMap.put(compName, typeMap);
            }
            //if the assetmap already contains the company name
            else{
                HashMap<String, Integer> typeMap = assetMap.get(compName);
                if(typeMap.containsKey(aType)){
                    int exisQty = typeMap.get(aType);
                    typeMap.put(aType, exisQty+qty);
                }

                else{
                    typeMap.put(aType, qty);
                }
            }
        }

        HashMap<String, HashMap<String, Integer>> benchMap = new HashMap<>();
        
        for(String asset: benchAssets){
            String[] comp = asset.split(",");
            String compName = comp[0];
            String aType = comp[1];
            int qty = Integer.parseInt(comp[2]);

            

        }

        System.out.println("Complete");
    }
}