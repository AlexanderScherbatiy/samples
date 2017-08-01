package sample.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

/**
 * Hello world!
 */
public class LuceneHelloWorld {
    public static void main(String[] args) throws Exception {

        StandardAnalyzer analyzer = new StandardAnalyzer();
        Directory indexDirectory = new RAMDirectory();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(indexDirectory, config);

        Document document = new Document();
        document.add(new TextField("content", "Hello, World!", Field.Store.YES));
        indexWriter.addDocument(document);


        document = new Document();
        document.add(new TextField("content", "Hello, Lucene!", Field.Store.YES));
        indexWriter.addDocument(document);
        indexWriter.close();

        IndexReader reader = DirectoryReader.open(indexDirectory);
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        QueryParser queryParser = new QueryParser("content", analyzer);

        query("hello", queryParser, indexSearcher);
        query("world", queryParser, indexSearcher);
        query("lucene", queryParser, indexSearcher);
        reader.close();
    }

    static void query(String query, QueryParser queryParser, IndexSearcher indexSearcher) throws Exception {

        TopDocs docs = indexSearcher.search(queryParser.parse(query), 10);
        ScoreDoc[] hits = docs.scoreDocs;

        System.out.printf("query: '%s'%n", query);
        for (ScoreDoc hit : hits) {
            Document doc = indexSearcher.doc(hit.doc);
            System.out.printf("score: %f, content: '%s'%n", hit.score, doc.get("content"));
        }
    }
}
