package com.yhl;

import com.yhl.util.SolrUtil;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrRequest;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.LBHttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.NamedList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SolrDemoApplicationTests {

    private final String url = "http://yhl:8080/sorl";

    @Test
    public void contextLoads() {
        SolrInputDocument solrInputFields = new SolrInputDocument();
    }

    @Autowired
    private SolrClient solrClient;

    @Test
    public void findAll() throws IOException, SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q", "id:1");
        QueryResponse query = solrClient.query(new SolrQuery("q", "id:1"));
        List<Map> solrDocumentListByList = SolrUtil.getSolrDocumentListByList(query.getResults());
        System.out.println(SolrUtil.getSolrDocumentListByMap(query.getResults()));
    }
}

