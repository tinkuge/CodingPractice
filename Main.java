import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Arrays;

public class Main {
  /**
   * Iterate through each line of input.
   */
  public static void main(String[] args) throws IOException {
    InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    BufferedReader in = new BufferedReader(reader);
    String line;
    while ((line = in.readLine()) != null) {
      Main.matchBenchmark(line);
    }
  }
  
  public static void matchBenchmark(String input) {
    //Split the input into portfolio and benchmark
    String[] portBench = input.split(":");
    //Assign the left part of the input to portfolio
    String portfolio = portBench[0];
    //Assign the right part of the input to bench
    String bench = portBench[1];
    //Split the portfolio and benchmark into individual assets
    String[] portAssets = portfolio.split("\\|");
    String[] benchAssets = bench.split("\\|");
    /*
      Inititate a map for portfolio assets such that it maps
      company name to Asset object with relevant fields populated
    */
    HashMap<String,Asset> portMap = new HashMap<>();
    //Call populateMap to iterate through each asset and assign corresponding
    //object fields
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
  
  //Standardized method to print output as described
  static void printTrade(String decision, String company, String aType, int qty) {
    //Get the absolute value to print
    qty = Math.abs(qty);
    System.out.println(decision+","+company+","+aType+","+qty);
  }
  
  //Populate map, take portfolio or benchmark entries and puts them in a map with corresponding company
  static void populateMap(HashMap<String, Asset> Map, String[] compColls){
    //Iterate through the component collections that contain
    //either portfolio or benchmark
    try{
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
    catch(NumberFormatException nfe){
      System.out.println("Argument to parseInt isn't a number");
      System.out.println(nfe);
    }
    catch(ArrayIndexOutOfBoundsException ae){
      System.out.println("Array index for indvComps is out of bounds. Track index carefully");
      System.out.println(ae);
    }
    
  }

}

/*
TypeAsset holds the constants that can be referred in the main class
*/
class TypeAsset{
  public static final String STOCK = "STOCK";
  public static final String BOND = "BOND";
  public static final String SELL = "SELL";
  public static final String BUY = "BUY";
}

/*
Asset class holds the blueprint for each asset
*/
class Asset{
  //variable to store company name
  String company;
  //Stores number of stocks (default 0)
  int stockQty;
  //Stores number of bonds (default 0)
  int bondQty;
  
  Asset(){
    //Initiate company to an empty string as default
    company = "";
    stockQty = 0;
    bondQty = 0;
  }
}