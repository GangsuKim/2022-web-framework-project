package com.example.crawl;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

// import java.net.URLDecoder;
import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class market {
    public static void main(String[] args) {
        ArrayList<String[]> itemList = homeplus("꼬깔콘");

        // for(String[] item: itemList) {
        //     System.out.println("===== New item =====");
        //     System.out.println("상품명 : " + item[0]);
        //     System.out.println("가격 : " + item[1]);
        //     System.out.println("단가 : " + item[2]);
        //     System.out.println("사진 URL : " + item[3]);
        // }
    }

    /**
     * 이마트의 상품 정보 크롤링
     * @param item 문자열 형태의 상품명
     * @return ArrayList 형태 [["상품명", "상품 가격", "상품 단가", "상품 사진 경로"],...]
     */
    static ArrayList<String[]> emart(String item){
        ArrayList<String[]> itemList = new ArrayList<>();

        try {
            Document doc = Jsoup.connect("https://emart.ssg.com/search.ssg?target=all&query=" + URLEncoder.encode(item, "UTF-8") + "&shpp=ssgem").userAgent("Mozilla").get();

            Element items = doc.getElementById("area_searchItemList"); // 아이템이 있는 Div 가져오기
            Elements itemDivs = items.getElementsByClass("mnemitem_goods"); // 상품 각각에 대해서 불러오기
    
            for(Element e: itemDivs) {
                if(e.text().contains("쓱배송")) {
                    String name = e.getElementsByClass("mnemitem_goods_tit").get(0).text(); // Item name
                    String price = e.getElementsByClass("ssg_price").get(0).text(); // Item price
                    String unitPrice = e.getElementsByClass("unit_price").get(0).text(); // Item unit price
                    String imageUrl = e.getElementsByClass("mnemitem_thmb_img").attr("src"); // Item image path
    
                    String[] tempArr = {name, price, unitPrice, "https:" + imageUrl};
    
                    itemList.add(tempArr);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return itemList;
    }

    static ArrayList<String[]> homeplus(String item) {
        ArrayList<String[]> itemList = new ArrayList<>();

        try {
            String uriItem = URLEncoder.encode(item, "UTF-8");
            String url = "https://front.homeplus.co.kr/express/search/item.json?addSubCategoryYn=Y&inputKeyword=" + uriItem + "&originalSearchYn=N&page=1&perPage=40&searchKeyword=" + uriItem + "&searchType=NONE&sort=RANK";
            String doc = Jsoup.connect(url).userAgent("Mozilla").ignoreContentType(true).execute().body();

            System.out.println(doc);
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(doc);

            System.out.println(jsonObject.get("data"));

            // Element items = doc.getElementsByClass("itemDisplayList").get(0); // 아이템이 있는 Div 가져오기
            // Elements itemDivs = items.getElementsByClass("unitItemBox");

            // for(Element e: itemDivs) {
            //     if(e.html().contains("즉시배송")) {
            //         String name = e.getElementsByClass("css-12cdo53-defaultStyle-Typography-ellips").get(0).text(); // Item name
            //         String price = e.getElementsByClass("priceValue").get(0).text(); // Item price
            //         String unitPrice = e.getElementsByClass("priceQty").get(0).text(); // Item unit price
            //         // String imageUrl = e.getElementsByClass("mnemitem_thmb_img").attr("src"); // Item image path

            //         System.out.println(name + price + unitPrice);
            //     }
            // }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException p) {
            p.printStackTrace();
        }

        return itemList;
    }
}
