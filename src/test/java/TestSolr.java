import cn.nyse.entity.Good;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.junit.Test;

import java.io.IOException;

public class TestSolr {
    @Test
    public void saveSolr() throws IOException, SolrServerException {
        HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/Goods");
        Good good = new Good("888", "潮牌手机", "很新颖很好玩哦", "http://aaa.jpg", 88888D);

        //如果没有则添加，如果有则修改
        solrServer.addBean(good);
        //提交事务
        solrServer.commit();
    }

    @Test
    public void deleteSolrById() throws IOException, SolrServerException {
        HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/Goods");
        solrServer.deleteById("888");
        solrServer.commit();
    }
    @Test
    public void deleteSolrByQuery() throws IOException, SolrServerException {
        HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr/Goods");
        solrServer.deleteByQuery("title:手机");
        solrServer.commit();

    }
}
