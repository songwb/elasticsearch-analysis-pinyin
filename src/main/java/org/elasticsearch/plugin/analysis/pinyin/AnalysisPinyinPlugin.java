package org.elasticsearch.plugin.analysis.pinyin;


import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.index.analysis.*;
import org.elasticsearch.indices.analysis.AnalysisModule;
import org.elasticsearch.plugins.AnalysisPlugin;
import org.elasticsearch.plugins.Plugin;

import java.util.HashMap;
import java.util.Map;


public class AnalysisPinyinPlugin extends Plugin implements AnalysisPlugin {

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> getTokenizers() {
        Map<String, AnalysisModule.AnalysisProvider<TokenizerFactory>> extra = new HashMap<>();
        extra.put("pinyin", PinyinTokenizerFactory::new);
        extra.put("pinyin_first_letter", PinyinAbbreviationsTokenizerFactory::new);
        extra.put("pinyin_multiple",PinyinTokenizerFactoryForMultiple::new);
        return extra;
    }

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<org.elasticsearch.index.analysis.TokenFilterFactory>> getTokenFilters() {
        Map<String, AnalysisModule.AnalysisProvider<org.elasticsearch.index.analysis.TokenFilterFactory>> extra = new HashMap<>();
        extra.put("pinyin", PinyinTokenFilterFactory::new);
        extra.put("pinyin_multiple",PinyinTokenFilterFactoryForMultiple::new);
        return extra;
    }

    @Override
    public Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> getAnalyzers() {
        Map<String, AnalysisModule.AnalysisProvider<AnalyzerProvider<? extends Analyzer>>> map = new HashMap<>();
        map.put("pinyin", PinyinAnalyzerProvider::new);
        map.put("pinyin_multiple",PinyinAnalyzerProviderForMultiple::new);
        return map;
    }
}
