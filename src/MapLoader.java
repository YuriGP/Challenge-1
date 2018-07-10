import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;


public class MapLoader {

    private static final String DELIMITER = "\\s+";


    public static Area[][] loadMap( final String path ) throws Exception {
        Stream<String> lines;
        try {
            lines = Files.lines( Paths.get( path ) );
        } catch ( IOException e ) {
            throw new Exception( "Unable to load the map", e );
        }

        Iterator<String> it = lines.iterator();
        String[] mapLength = it.next().split( DELIMITER );
        int x = Integer.parseInt( mapLength[0] );
        int y = Integer.parseInt( mapLength[1] );

        Area[][] map = new Area[x][y];

        int rowCount = 0;
        while ( it.hasNext() ) {
            String line = it.next();
            String[] row = line.split( DELIMITER );

            Area[] rowAreas = new Area[row.length];
            for ( int i = 0; i < row.length; ++i ) {
                rowAreas[i] = Area.builder()
                                  .elevation( Integer.parseInt( row[i] ) )
                                  .build();
            }

            map[rowCount] = rowAreas;

            ++rowCount;
        }

        return map;
    }
}
