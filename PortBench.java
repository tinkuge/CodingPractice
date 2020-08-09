import java.lang.ProcessBuilder.Redirect.Type;
import java.util.Arrays;
import java.util.HashMap;

public class PortBench {
    public static final String STOCK = "STOCK";
    public static final String BOND = "BOND";
    public static void main(String[] args) {
        //String input = "Vodafone,STOCK,10|Google,STOCK,15:Vodafone,STOCK,15|Vodafone,BOND,10|Google,STOCK,10";
        String input = "Vodafone,STOCK,10|Google,STOCK,15|Microsoft,BOND,15:Vodafone,STOCK,15|Google,STOCK,10|Microsoft,BOND,5|Apple,BOND,15|Zillow,Stock,20";
        matchBenchmark(input);
    }

    static void matchBenchmark(String input){
        
        
        String[] portBench = input.split(":");
        String portfolio = portBench[0];
        String bench = portBench[1];

        String[] portAssets = portfolio.split("\\|");
        String[] benchAssets = bench.split("\\|");

        

        //Iterate through each entry in portfolio array and assign the relevant fields
        HashMap<String, Asset> portMap = new HashMap<>();
        populateMap(portMap, portAssets);

        HashMap<String, Asset> benchMap = new HashMap<>();
        populateMap(benchMap, benchAssets);
        // for(int i = 0; i < portAssets.length; i++){
        //     String[] indvComps = portAssets[i].split(",");
        //     String compName = indvComps[0];
        //     String aType = indvComps[1];
        //     int qty = Integer.parseInt(indvComps[2]);
        //     if(portMap.containsKey(compName)){
        //         Asset currAsset = portMap.get(compName);
        //         if(aType.equals(TypeAsset.BOND)){
        //             currAsset.bondQty = currAsset.bondQty+qty;
        //         }
        //         else{
        //             currAsset.stockQty = currAsset.stockQty+qty;
        //         }
        //     }

        //     else{
        //         Asset currAsset = new Asset();
        //         currAsset.company = compName;
        //         if(aType.equals(TypeAsset.BOND)){
        //             currAsset.bondQty = qty;
        //         }

        //         else{
        //             currAsset.stockQty = qty;
        //         }

        //         portMap.put(compName, currAsset);
        //     }
        // }

        
        // for(int i = 0; i < benchAssets.length; i++){
        //     String[] indvComps = benchAssets[i].split(",");
        //     String compName = indvComps[0];
        //     String aType = indvComps[1];
        //     int qty = Integer.parseInt(indvComps[2]);

        //     if(benchMap.containsKey(compName)){
        //         Asset currAsset = benchMap.get(compName);
        //         if(aType.equals(TypeAsset.BOND)){
        //             currAsset.bondQty = currAsset.bondQty+qty;
        //         }
        //         else{
        //             currAsset.stockQty = currAsset.stockQty+qty;
        //         }
        //     }

        //     else{
        //         Asset currAsset = new Asset();
        //         currAsset.company = compName;
        //         if(aType.equals(TypeAsset.BOND)){
        //             currAsset.bondQty = qty;
        //         }

        //         else{
        //             currAsset.stockQty = qty;
        //         }

        //         benchMap.put(compName, currAsset);
        //     }
        // }

        Asset[] portObjs = portMap.values().toArray(new Asset[0]);
        //sort the above array by company name
        Arrays.sort(portObjs, (a,b) -> a.company.compareTo(b.company));
        Asset[] benchObjs = benchMap.values().toArray(new Asset[0]);
        //sort bench objects by company name
        Arrays.sort(benchObjs, (a,b) -> a.company.compareTo(b.company));
        
        int pi = 0;
        int bi = 0;

        //iterate until one of the object arrays run out
        while(pi < portObjs.length && bi < benchObjs.length){
            Asset currPortOb = portObjs[pi];
            Asset currBenObj = benchObjs[bi];

            //String precedence value
            int precVal = currPortOb.company.compareTo(currBenObj.company);

            //if both have same company names
            if(precVal == 0){
                //Both arrays have same company names
                String cname = currPortOb.company;
                //check bonds first, then stocks
                
                int bdiff = currPortOb.bondQty - currBenObj.bondQty;

                if(bdiff > 0){//sell
                    printTrade(TypeAsset.SELL, cname, TypeAsset.BOND, bdiff);
                }

                if(bdiff < 0){
                    printTrade(TypeAsset.BUY, cname, TypeAsset.BOND, bdiff);
                }

                //check stocks now
                int sdiff = currPortOb.stockQty - currBenObj.stockQty;

                if(sdiff > 0){
                    printTrade(TypeAsset.SELL, cname, TypeAsset.STOCK, sdiff);
                }

                if(sdiff < 0){
                    printTrade(TypeAsset.BUY, cname, TypeAsset.STOCK, sdiff);
                }

                pi++;
                bi++;
            }
            //portfolio company comes alphabetically before benchmark
            else if(precVal < 0){
                //sell the company shares as the benchmark doesn't have the company
                if(currPortOb.bondQty > 0){
                    printTrade(TypeAsset.SELL, currPortOb.company, TypeAsset.BOND, currPortOb.bondQty);
                }

                if(currPortOb.stockQty > 0){
                    printTrade(TypeAsset.SELL, currPortOb.company, TypeAsset.STOCK, currPortOb.stockQty);
                }

                pi++;
            }

            //benchmark company comes first alphabetically
            else{
                if(currBenObj.bondQty > 0){
                    printTrade(TypeAsset.BUY, currBenObj.company, TypeAsset.BOND, currBenObj.bondQty);
                }

                if(currBenObj.stockQty > 0){
                    printTrade(TypeAsset.BUY, currBenObj.company, TypeAsset.STOCK, currBenObj.stockQty);
                }

                bi++;
            }
        }

        //deal with leftover entries in other portfolio or benchmark if any
        if(pi < portObjs.length){
            //benchmark objs exhausted
            //sell everything else
            while(pi < portObjs.length){
                Asset currPortObj = portObjs[pi];

                if(currPortObj.bondQty > 0){
                    printTrade(TypeAsset.SELL, currPortObj.company, TypeAsset.BOND, currPortObj.bondQty);
                }

                if(currPortObj.stockQty > 0){
                    printTrade(TypeAsset.SELL, currPortObj.company, TypeAsset.STOCK, currPortObj.stockQty);
                }
                pi++;
            }
        }

        else{
            while(bi < benchObjs.length){
                Asset currBenObj = benchObjs[bi];

                if(currBenObj.bondQty > 0){
                    printTrade(TypeAsset.BUY, currBenObj.company, TypeAsset.BOND, currBenObj.bondQty);

                }

                if(currBenObj.stockQty > 0){
                    printTrade(TypeAsset.BUY, currBenObj.company, TypeAsset.STOCK, currBenObj.stockQty);
                }
                bi++;
            }
        }

        System.out.println("Complete");

    }

    private static void populateMap(HashMap<String, Asset> Map, String[] compColls) {
        for(int i = 0; i < compColls.length; i++){
            String[] indvComps = compColls[i].split(",");
            String compName = indvComps[0];
            String aType = indvComps[1];
            int qty = Integer.parseInt(indvComps[2]);

            
            if(Map.containsKey(compName)){
                Asset currAsset = Map.get(compName);
                if(aType.equals(TypeAsset.BOND)){
                    currAsset.bondQty = currAsset.bondQty+qty;
                }
                else{
                    currAsset.stockQty = currAsset.stockQty+qty;
                }
            }

            else{
                Asset currAsset = new Asset();
                currAsset.company = compName;
                if(aType.equals(TypeAsset.BOND)){
                    currAsset.bondQty = qty;
                }

                else{
                    currAsset.stockQty = qty;
                }

                Map.put(compName, currAsset);
            }
        }
    }

    static void printTrade(String decision, String company, String aType, int qty) {
        qty = Math.abs(qty);
        System.out.println(decision+","+company+","+aType+","+qty);
    }
}

class TypeAsset{
    public static final String STOCK = "STOCK";
    public static final String BOND = "BOND";
    public static final String SELL = "SELL";
    public static final String BUY = "BUY";
}

class Asset{
    
    String company;
    int stockQty;
    int bondQty;

    Asset(){
        stockQty = 0;
        bondQty = 0;
    }
}