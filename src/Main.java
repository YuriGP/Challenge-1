public class Main {

    private static String getEmail( Slope slope ) {
        StringBuilder result = new StringBuilder();
        result.append( slope.getPath().size() );
        result.append( slope.getDrop() );
        result.append( "@redmart.com" );

        return result.toString();
    }


    public static void main( String[] args ) throws Exception {
        Area[][] map = MapLoader.loadMap( "data/map.txt" );

        Mountain mountain = new Mountain( map );
        Slope slope = mountain.findBestSlope();

        System.out.println( getEmail( slope ) );
    }
}
