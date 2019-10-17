package eggventory;

import eggventory.items.Stock;
import eggventory.items.StockType;

import java.util.ArrayList;

public class StockList {
    private ArrayList<StockType> stockList;

    /**
     * Constructs a new StockList object using an already existing stockList.
     * @param stockList ArrayList<> of StockType objects. There should already be a default "Uncategorised" StockType.
     */
    public StockList(ArrayList<StockType> stockList) {
        this.stockList = stockList;
    }

    /**
     * Constructs a new StockList object with one default StockType, "Uncategorised".
     */
    public StockList() {
        this.stockList = new ArrayList<>();
        this.stockList.add(new StockType("Uncategorised", false));
    }

    /**
     * Gets the whole stockList. Note: technically doing using this method will violate OOP.
     * @return the list.
     */
    public ArrayList<StockType> getList() {
        return stockList;
    }

    /**
     * Returns a stockType from stockList if it exits else retuns a null StockType.
     * @param stockType The unique string that identifies a stockType
     * @return stockType of stockList
     */
    public StockType getStockType(String stockType) {
        StockType nullType = new StockType("NULL", true);
        for (StockType stType : stockList) {
            if (stType.getName().equals(stockType)) {
                return stType;
            }
        }
        return nullType;
    }

    /**
     * Gets the total number of stockTypes in this stockList. Not to be confused with getStockQuantity.
     * @return the number of stockTypes.
     */
    public int getStockTypeQuantity() { //The number of stockTypes in the list.
        return stockList.size();
    }

    /**
     * Gets the total number of stocks in this stockList. This sums the number of stocks across stockTypes.
     * @return the total number of stocks.
     */
    public int getStockQuantity() { //The number of stocks in the list, across all stockTypes.
        int total = 0;
        for (StockType stockType : stockList) {
            total += stockType.getQuantity();
        }

        return total;
    }

    public void addStockType(String name) {
        stockList.add(new StockType(name, false));
    }

    /**
     * Checks whether mentioned stockType already exists.
     * @param stockType A String matching exactly the StockType to add the new Stock object under.
     * @param stockCode A unique String that identifies the Stock.
     * @param quantity Quantity of the stock.
     * @param description A String describing the nature of the Stock object.
     */
    public void addStock(String stockType, String stockCode, int quantity, String description) {
        for (StockType listType: stockList) {
            if (listType.getName().equals(stockType)) {
                listType.addStock(stockType, stockCode, quantity, description);
                return;
            }
        }

        // "Uncategorised" is always the first element on stockList.
        stockList.get(0).addStock("Uncategorised", stockCode, quantity, description);
    }

    /**
     * Deletes a Stock object from a list.
     * @param stockCode The unique String that identifies a Stock.
     * @return the stock that was deleted, for printing purposes.
     */
    public Stock deleteStock(String stockCode) {
        Stock deleted;
        for (StockType stockType : stockList) {
            deleted = stockType.deleteStock(stockCode);
            if (deleted !=  null) { //If something WAS deleted
                return deleted;
            }
        }
        return null;
    }

    /**
     * Deletes a StockType object, and all the stocks under it.
     * @param stockTypeName The name of the StockType to delete.
     * @return the stockType deleted, for printing purposes.
     */
    public StockType deleteStockType(String stockTypeName) {
        StockType deleted;
        for (StockType stockType : stockList) {
            if (stockTypeName.equals(stockType.getName())) {
                deleted = stockType;
                stockList.remove(stockType);
                return deleted;
            }
        }
        return null;
    }

    /**
     * Prints every stock within stocklist. Should only be called by Cli.
     * @return The string of the stocklist.
     */
    public String toString() {
        String ret = "";
        ret += "CURRENT INVENTORY\n";

        for (StockType stocktype : stockList) {
            ret += "------------------------\n";
            ret += stocktype.toString() + "\n";
        }

        return ret;
    }

    /**
     * Saves the list into a String.
     * @return The String that will be directly saved into file.
     */
    public String saveDetailsString() {
        String details = "";

        for (StockType stocktype : stockList) {
            details += stocktype.saveDetailsString() + "\n";
        }

        return details;
    }

}