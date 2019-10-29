import cn.nyse.entity.Good;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TestQuery {

    HttpSolrServer solrServer = null;

    @Before
    public void before() {
        solrServer = new HttpSolrServer("http://localhost:8080/solr/Goods");
    }


    @Test
    public void testQuery() throws SolrServerException {
        SolrQuery solrQuery = new SolrQuery("title:商务");
        QueryResponse response = solrServer.query(solrQuery);
        System.out.println("查询条数：" + response.getResults().getNumFound());

        List<Good> goods = response.getBeans(Good.class);
        for (Good good : goods) {
            System.out.println(good);
        }
    }

    @Test
    public void testQuery2() throws SolrServerException {
        SolrQuery solrQuery = new SolrQuery("title:手机");

        //添加过滤条件
        solrQuery.addFilterQuery("price:[1000 TO 9000]");
        //排序
        solrQuery.addSort("price", SolrQuery.ORDER.desc);
        //分页
        solrQuery.setStart(1);
        solrQuery.setRows(5);

        //设置查询域
        solrQuery.setFields("id,name,title,price");

        //开启高亮
        solrQuery.setHighlight(true);

        //设置高亮域
        solrQuery.addHighlightField("title");
        //设置高亮域
        solrQuery.addHighlightField("name");
        //高亮前缀
        solrQuery.setHighlightSimplePre("<font style='color:red'");
        //高亮后缀
        solrQuery.setHighlightSimplePost("</font>");

        QueryResponse response = solrServer.query(solrQuery);
        System.out.println("查询条数：" + response.getResults().getNumFound());

        List<Good> goods = response.getBeans(Good.class);
        for (Good good : goods) {
            System.out.println(good);
        }
        System.out.println("----------------------");
        System.out.println("高亮数据: ");
        Map<String, Map<String, List<String>>> high = response.getHighlighting();
        for (Good good : goods) {
            if (high.get(good.getId()).get("title") != null) {
                String title = high.get(good.getId()).get("title").get(0);
                System.out.println("title:" + title);
            }
            if (high.get(good.getId()).get("name") != null) {
                String name = high.get(good.getId()).get("name").get(0);
                System.out.println("name:" + name);
            }
            System.out.println("high.id:" + high.get(good.getId()));
        }
    }

    @After
    public void after() throws IOException, SolrServerException {
        solrServer.commit();
    }
}
