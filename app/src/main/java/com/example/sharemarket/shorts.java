package com.example.sharemarket;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sharemarket.adapter.VersionsAdapter;
import com.example.sharemarket.adapter.VersionsAdapter;
import com.example.sharemarket.model.Versions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.example.sharemarket.model.versions;

import java.util.ArrayList;
import java.util.List;

public class shorts extends AppCompatActivity {

   RecyclerView recyclerView;
   List<Versions> versionsList;
 BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shorts);

        recyclerView = findViewById(R.id.qandarecyclerview);

        getSupportActionBar().setTitle("SHARE MARKET");
        getSupportActionBar().setSubtitle("Shorts");

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.shortsss);

        getSupportActionBar().setTitle("SHARE MARKET");
        getSupportActionBar().setSubtitle("shorts");

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.courses:
                        return true;

                    case R.id.ebook:
                        startActivity(new Intent(getApplicationContext(),Ebook.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.nav_profile:
                        startActivity(new Intent(getApplicationContext(),setting.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.shortsss:
                        return true;



                }

                return false;
            }
        });


        initData();
        setRecyclerView();


    }



    public void setRecyclerView() {
        recyclerView = findViewById(R.id.qandarecyclerview);
        VersionsAdapter versionsAdapter = new VersionsAdapter(versionsList);
        recyclerView.setAdapter(versionsAdapter);
        recyclerView.setHasFixedSize(true);
    }

    public void initData(){
        versionsList = new ArrayList<>();

        versionsList.add(new Versions("what is technical analysis in stock market", "Technical analysis is a trading discipline employed to evaluate investments and identify trading opportunities in price trends and patterns seen on charts. Technical analysts believe past trading activity and price changes of a security can be valuable indicators of the security's future price movements."));
        versionsList.add(new Versions("what is fundamental analysis in stock market", "Fundamental analysis is a method of determining a stock's real or \"fair market\" value. Fundamental analysts search for stocks that are currently trading at prices that are higher or lower than their real value."));
        versionsList.add(new Versions("what is cryptocurrency in stock market", "Cryptocurrency, sometimes called crypto-currency or crypto, is any form of currency that exists digitally or virtually and uses cryptography to secure transactions. Cryptocurrencies don't have a central issuing or regulating authority, instead using a decentralized system to record transactions and issue new units."));
        versionsList.add(new Versions("what is futures and options", "A Future is a contract to buy or sell an underlying stock or other asset at a pre-determined price on a specific date. On the other hand, Options contract gives an opportunity to the investor the right but not the obligation to buy or sell the assets at a specific price on a specific date, known as the expiry date."));
        versionsList.add(new Versions("what is stock market", "The stock market consists of exchanges or OTC markets in which shares and other financial securities of publicly held companies are issued and traded."));


        versionsList.add(new Versions( "Can I trade when markets are closed or shut down?","You cannot trade after the market is closed or shut down. Even though trading no more requires physical presence it isn’t possible to trade after the market is shut down. You can only trade between 09:15 am to 3:30 pm, however many passive investors trade after trading hours. The orders placed after trading hours are called AMO or After Market Orders and they sometimes create a volatile market. AMO also causes price fluctuations in the share price."));
        versionsList.add(new Versions(" How many Sectors are there to invest in Stock Market?", "You can invest in 11 different sectors in the stock market. This type of industry categorization helps the portfolio manager create a diverse portfolio and allocate funds more efficiently."));
        versionsList.add(new Versions("Is there any time for buying shares or doing a trade?", "Yes, you can only trade between 09:15 am to 3:30 pm on weekdays. But you can place AMO type of orders after these trading hours.\n"));
        versionsList.add(new Versions("Is it safe to invest in Unlisted Stocks as a beginner?", "Many traders have stock market questions related to unlisted stocks. Investing in unlisted stocks requires expertise and sound knowledge of the stocks. Beginners often lack this knowledge and can incur losses. But if your firm about the future growth of the company only then you can think of investing in unlisted stocks"));
        versionsList.add(new Versions("How to Find Undervalued Stocks?", "Undervalued stocks are stocks that are priced lower than their fair price. Investors find these stocks using fundamental and technical analysis. Fundamental analysis involves calculating asset value by analyzing external influences like industry trends. Technical analysis includes evaluation of price movements with the help of historic data. With these methods, traders evaluate the fair price of the undervalued stocks. You can always research more to get answers to your share market questions related to undervalued stocks."));
        versionsList.add(new Versions(" How to find good companies as there are many publicly listed companies in the Indian stock market?", "You can find many online tools to find good stocks. You can use the stock screener to find good stocks from the pool of all the companies listed on the stock exchange. You can use different filters like valuations, the market cap of the company, so on."));
        versionsList.add(new Versions("How much time should I spend while researching stocks?", "Researching stocks depends on the type of investment. If it’s trading and not long term investment you can rely on historic charts, price trends, so on. and you need not invest a lot of time in research. If you have long term investment plans you need to do thorough research about the company. You need to research the company fundamentals, analyze financial statements, competitor analysis, etc. if the investment period is of more than a year."));
        versionsList.add(new Versions(" Where can I get the company’s financial report and other information?", "You can find all the financial reports of a company on the company website or from stock exchanges (NSE or BSE). You can also get your hands on the annual report of the company and analyze financial statements in depth."));
        versionsList.add(new Versions("How to invest/apply for an IPO online?", "You can invest or apply online in IPO using your trading account.\n" +
                "a. Using Trading account\n" +
                "\n" +
                "b. Login to your trading account and select the required IPO\n" +
                "\n" +
                "c. On the trading, portal input the number of shares you want to buy and the price of the shares.\n" +
                "\n" +
                "d. Once done click on submit"));
        versionsList.add(new Versions("Is investing in small-caps more profitable than bluechip companies?", "Before you invest in any stocks you need to review the future prospects of the company. All the small cap companies have higher growth potential as compared to the bluechip companies. But the large cap companies have already made a place in the market and they give good returns to the shareholders. To sum up, investing in small caps is sometimes more beneficial if the future prospects of the organization are promising."));
        versionsList.add(new Versions("Should I invest in stocks when the market is at high?", "It is one of the frequently asked questions in stock market. In this scenario create a watchlist when the market is high and keep an eye on the stocks. Once you identify good stocks, average out the stocks, this will reduce the chances of buying stocks at a high price."));
        versionsList.add(new Versions("How many stocks should I buy in my portfolio?", "The portfolio should not be either over diversified or under diversified. An over-diversified portfolio can make tracking a hassle and does not yield good results. On the other hand, if you invest in limited stocks in your portfolio then the falling of one stock will also impact your portfolio adversely."));
        versionsList.add(new Versions(" How much returns can I expect from the market?", "The returns depend on your performing and non-performing stocks. When you have a properly diversified portfolio some of the stocks may perform well while some of them will not this will affect the returns."));
        versionsList.add(new Versions("Should I use a stop loss on my investments?", "It depends if you are a trader or a long term investor. If you are an active trader, you can use stop loss to control a lot of damage. But if you are a long term investor you can avoid using stop loss as the losses in long term are caused due to short term fluctuations in the market. Also, in the case of long term investments, you should prefer buying more stocks when the prices drop rather than selling them."));
        versionsList.add(new Versions("Can I become a millionaire by investing in stocks?", "This is one of the common questions about the stock market and the answer is yes. Having said that becoming a millionaire by investing in stocks is not easy and requires a lot of diligence. If you wish to earn from the stock market you have to put a lot of time and effort into researching companies.\n" +
                "\n" +
                "To conclude the stock market is an amazing investment option but not everyone is attracted to it and mostly it is due to the fear of losing money. But with enough time and efforts, many can use this as an opportunity to create wealth. Hope you find these stock market FAQs helpful with your investment journey."));


    }


}