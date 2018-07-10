import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Slope implements Comparable<Slope> {

    private final List<Area> path;
    private final int drop;


    public Slope( final List<Area> path ) {
        this.path = path;

        Collections.sort( this.path );

        this.drop = this.path.get( 0 ).getElevation() -
                    this.path.get( this.path.size() - 1 ).getElevation();
    }


    public List<Area> getPath() {
        return this.path;
    }


    public int getDrop() {
        return drop;
    }


    public static SlopeBuilder builder() {
        return new SlopeBuilder();
    }


    @Override
    public int compareTo( final Slope slope ) {
        if ( slope == null ) {
            return 1;
        }

        if ( path.size() != slope.path.size() ) {
            return Integer.compare( path.size(), slope.path.size() );
        }
        if ( drop != slope.drop ) {
            return Integer.compare(drop , slope.drop );
        }
        return 0;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( "Slope (" );
        builder.append( drop );
        builder.append( " [" );
        for ( int i = 0; i < path.size(); ++i ) {
            builder.append( path.get( i ).getElevation() )
                   .append( i < path.size() - 1 ? " " : "" );
        }
        builder.append( "])" );

        return builder.toString();
    }


    public static final class SlopeBuilder {

        private List<Area> path = new ArrayList<>();


        public SlopeBuilder() {

        }


        public SlopeBuilder path( final List<Area> path ) {
            this.path = path;
            return this;
        }


        public Slope build() {
            return new Slope( path );
        }

    }
}
