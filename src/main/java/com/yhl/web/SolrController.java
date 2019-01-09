package com.yhl.web;

import com.yhl.entity.Inf;
import com.yhl.util.SolrUtil;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.NamedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class SolrController {

    /**
     * 清空solr里的所有
     * <delete><query>*:*</query></delete>
     * <commit/>
     */
    @Autowired
    private SolrClient solrClient;

    /**
     * 添加多个对象
     *
     * @return
     * @throws IOException
     * @throws SolrServerException
     */
    @GetMapping("/adds")
    public Object adds() throws IOException, SolrServerException {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
//            Map m = new HashMap();
//            m.put("id", ""+i);
//            m.put("name", "长江" + (i + 1) + "号");
//            m.put("age", (i * 50));
//            SolrInputDocument m = new SolrInputDocument();
//            m.addField("id", i);
//            m.addField("name", "长江" + (i + 1) + "号");
//            m.addField("age", (i * 50));
//            list.add(m);
            list.add(new Inf(i + "", "长江" + (i + 1) + "号", (i * 50)));
        }
        solrClient.addBeans(list);
        solrClient.commit();
        return list;
    }

    @GetMapping("/add")
    public Object add() throws Exception {
        int i = 101;
        Inf inf = new Inf(i + "", "长江" + (i + 1) + "号", (i * 50));
        solrClient.addBean(inf);
        return inf;
    }

    @GetMapping("/del")
    public Object del() throws IOException, SolrServerException {
        //根据id删除信息
        UpdateResponse updateResponse = solrClient.deleteById("0");
        //执行的时间
        long elapsedTime = updateResponse.getElapsedTime();
        int qTime = updateResponse.getQTime();
        //请求地址
        String requestUrl = updateResponse.getRequestUrl();
        //请求的结果{responseHeader={status=0,QTime=2}}
        NamedList<Object> response = updateResponse.getResponse();
        //请求结果的头{status=0,QTime=2}
        NamedList responseHeader = updateResponse.getResponseHeader();
        //请求的状态 0
        int status = updateResponse.getStatus();
        System.out.println("elapsedTime===========" + elapsedTime);
        System.out.println("qTime===========" + qTime);
        System.out.println("requestUrl===========" + requestUrl);
        System.out.println("response===========" + response);
        System.out.println("responseHeader===========" + responseHeader);
        System.out.println("status===========" + status);
        return null;
    }

    /**
     * 查询
     */
    @GetMapping("/findAll")
    public void findAll() throws IOException, SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.set("q", "id:1");
        QueryResponse query = solrClient.query(new SolrQuery("q","id:1"));
        List<Map> solrDocumentListByList = SolrUtil.getSolrDocumentListByList(query.getResults());
        System.out.println(solrDocumentListByList);
    }
}
