package com.yhl.util;

import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SolrUtil {
    public static List<Map> getSolrDocumentListByList(SolrDocumentList slist) {
        List l = new ArrayList();
        if (slist == null) return null;
        for (SolrDocument d : slist) {
            l.add(d.getFieldValueMap());
        }
        return l;
    }

    public static Map getSolrDocumentListByMap(SolrDocumentList slist) {
        return getSolrDocumentListByList(slist).get(0);
    }
}
