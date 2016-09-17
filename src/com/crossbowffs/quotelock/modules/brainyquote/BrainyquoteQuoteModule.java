package com.crossbowffs.quotelock.modules.brainyquote;

import android.content.ComponentName;
import android.content.Context;
import com.crossbowffs.quotelock.api.QuoteData;
import com.crossbowffs.quotelock.api.QuoteModule;
import com.crossbowffs.quotelock.utils.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class BrainyquoteQuoteModule implements QuoteModule {
    int m_displayNameRes;
    String m_URL;

    protected BrainyquoteQuoteModule(int displayNameRes, String quoteCode) {
        m_displayNameRes = displayNameRes;
        m_URL = String.format("http://feeds.feedburner.com/brainyquote/QUOTE%s", quoteCode);
    }

    @Override
    public String getDisplayName(Context context) {
        return context.getString(m_displayNameRes);
    }

    @Override
    public ComponentName getConfigActivity(Context context) {
        return null;
    }

    @Override
    public int getMinimumRefreshInterval(Context context) {
        return 86400;
    }

    @Override
    public boolean requiresInternetConnectivity(Context context) {
        return true;
    }

    @Override
    public QuoteData getQuote(Context context) throws IOException {
        String rssXml = IOUtils.downloadString(this.m_URL);
        Document document = Jsoup.parse(rssXml);

        String quoteText = document.select("item > description").first().text();
        String quoteSource = String.format("― %s", document.select("item > title").first().text());

        return new QuoteData(quoteText.substring(1, quoteText.length()-1), quoteSource);
    }
}
