package com.hp.hpl.jena.sparql.xpath3;

import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.sparql.function.FunctionRegistry;
import com.hp.hpl.jena.util.FileManager;

/**
 * Created by dimuthuupeksha on 3/14/15.
 */
public class Main {
    public static void main(String ar[]){
        FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
        FunctionRegistry.get().put("http://jena.org/xpath3#floor", XPath3_Floor.class) ; //registering function

        Model model = FileManager.get().loadModel(Main.class.getClassLoader().getResource("sample.rdf").getPath());
        String queryString ="PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "+
                "PREFIX cd: <http://www.recshop.fake/cd#> "+
                "PREFIX xs: <http://www.w3.org/2001/XMLSchema#> "+
                "PREFIX xp3: <http://jena.org/xpath3#> "+
                "SELECT * WHERE { "+
                "?Description cd:price ?x ." +
                "FILTER (xp3:floor(xs:double(?x)) > 9.50) "+   //invoking floor
                "}";
        Query query = QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query,model);
        try{
            ResultSet rst = qexec.execSelect();
            while(rst.hasNext()){
                QuerySolution sol = rst.nextSolution();
                Literal desc = sol.getLiteral("x");
                System.out.println(desc);
            }
        }finally {
            qexec.close();
        }

    }
}
