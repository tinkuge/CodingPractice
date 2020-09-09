import java.lang.ProcessBuilder.Redirect.Type;
import java.util.Arrays;
import java.util.HashMap;

public class PortBench {
    public static final String STOCK = "STOCK";
    public static final String BOND = "BOND";
    public static void main(String[] args) {
        //String input = "Vodafone,STOCK,10|Google,STOCK,15:Vodafone,STOCK,15|Vodafone,BOND,10|Google,STOCK,10";
        //String input = "Vodafone,STOCK,10|Google,STOCK,15|Microsoft,BOND,15:Vodafone,STOCK,15|Google,STOCK,10|Microsoft,BOND,15";
        //String input = "Vodafone,STOCK,10|Google,STOCK,15|Microsoft,BOND,15:Vodafone,STOCK,15|Google,STOCK,10|Microsoft,BOND,15|Zillow,STOCK,10|Apple,BOND,5";
        String input = "Vodafone,STOCK,10:Zillow,STOCK,10";
        matchBenchmark(input);
    }

    static void matchBenchmark(String input){
        
        //Split the input into portfolio and benchmark
        String[] portBench = input.split(":");
        String portfolio = portBench[0];
        String bench = portBench[1];

        //Split the portfolio into individual assets
        String[] portAssets = portfolio.split("\\|");
        //Split the benchmark into individual assets
        String[] benchAssets = bench.split("\\|");

        /*
            Initiate a map that maps company name in the portfolio to an asset
            object that contains compnay name, bond and stock values
        */
        HashMap<String, Asset> portMap = new HashMap<>();
        //Iterate through each asset in portfolio array and assign the relevant object fields
        populateMap(portMap, portAssets);
        
        HashMap<String, Asset> benchMap = new HashMap<>();
        //Iterate through each asset in benchmark array and assign the relevant object fields
        populateMap(benchMap, benchAssets);

        /*
            Now that the maps are populated, get the values of the maps in an array
            so as to sort them and prep them for comparison
        */
        Asset[] portObjs = portMap.values().toArray(new Asset[0]);
        //sort the above array by company name
        Arrays.sort(portObjs, (a,b) -> a.company.compareTo(b.company));
        Asset[] benchObjs = benchMap.values().toArray(new Asset[0]);
        //sort bench objects by company name
        Arrays.sort(benchObjs, (a,b) -> a.company.compareTo(b.company));
        
        //pi is portfolio index, used to to keep track of elements in portfolio
        //object array
        int pi = 0;
        //Similarly, bi is used to keep track of elements in benchmark object array
        int bi = 0;

        //iterate until one of the object arrays run out
        while(pi < portObjs.length && bi < benchObjs.length){
            //Get each individual asset at the current index
            Asset currPortOb = portObjs[pi];
            Asset currBenObj = benchObjs[bi];

            //Check whether portfolio entry comes first alphabetically
            //or the benchmark entry (based on company name)
            int precVal = currPortOb.company.compareTo(currBenObj.company);

            //if both have same company names
            if(precVal == 0){
                //Get the company name
                String cname = currPortOb.company;
                //check bonds first and then stocks, since bond related trades are  printed first

                //Get the difference in quantities of current portfolio bonds and current benchmark bonds
                int bdiff = currPortOb.bondQty - currBenObj.bondQty;

                //If the difference is positive, it means portfolio currently has
                //excess bonds that it needs to shed
                if(bdiff > 0){//sell
                    //Call the printTrade to print the trade in requisite format
                    printTrade(TypeAsset.SELL, cname, TypeAsset.BOND, bdiff);
                }

                //If the difference is negative, it means portfolio currently has
                //a deficit in bonds that need to be bought
                if(bdiff < 0){
                    //Call the printTrade to print the trade in requisite format
                    printTrade(TypeAsset.BUY, cname, TypeAsset.BOND, bdiff);
                }

                //if the difference is zero, no trade is done. Nothing is printed

                //Repeat the above procedure for stocks too
                int sdiff = currPortOb.stockQty - currBenObj.stockQty;

                if(sdiff > 0){
                    printTrade(TypeAsset.SELL, cname, TypeAsset.STOCK, sdiff);
                }

                if(sdiff < 0){
                    printTrade(TypeAsset.BUY, cname, TypeAsset.STOCK, sdiff);
                }

                //advance the portfolio and benchmark indices
                pi++;
                bi++;
            }

            //portfolio company comes alphabetically before benchmark
            else if(precVal < 0){
                //sell the company shares (bonds or stocks) as the benchmark doesn't include the company
                if(currPortOb.bondQty > 0){
                    printTrade(TypeAsset.SELL, currPortOb.company, TypeAsset.BOND, currPortOb.bondQty);
                }

                if(currPortOb.stockQty > 0){
                    printTrade(TypeAsset.SELL, currPortOb.company, TypeAsset.STOCK, currPortOb.stockQty);
                }
                //advance the portfolio index
                pi++;
            }

            //benchmark company comes first alphabetically
            else{
                //Buy the company shares (bonds or stocks) as the portfolio currently have the company shares
                if(currBenObj.bondQty > 0){
                    printTrade(TypeAsset.BUY, currBenObj.company, TypeAsset.BOND, currBenObj.bondQty);
                }

                if(currBenObj.stockQty > 0){
                    printTrade(TypeAsset.BUY, currBenObj.company, TypeAsset.STOCK, currBenObj.stockQty);
                }
                //advance the benchmark index
                bi++;
            }
        }

        //deal with leftover entries in other portfolio or benchmark if any

        //There are leftover assets in portfolio
        //Benchmark objs exhausted
        //Sell everything else left in portfolio
        if(pi < portObjs.length){
            
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

        //Leftover assets in benchmark
        //Buy the relevant shares of the entries encountered
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