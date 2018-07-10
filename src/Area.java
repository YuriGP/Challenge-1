import java.util.ArrayList;
import java.util.List;


public class Area implements Comparable<Area> {

    private final int elevation;
    private final List<Area> paths;

    private Slope bestSlope;


    private Area( final int elevation, final List<Area> paths ) {
        this.elevation = elevation;
        this.paths = paths;
    }


    public int getElevation() {
        return this.elevation;
    }


    public boolean addPath( final Area area ) {
        return this.paths.add( area );
    }


    public List<Area> getPaths() {
        return this.paths;
    }


    public Slope getBestSlope() {
        return bestSlope;
    }


    public void setBestSlope( final Slope bestSlope ) {
        this.bestSlope = bestSlope;
    }


    public static AreaBuilder builder() {
        return new AreaBuilder();
    }


    @Override
    public int compareTo( final Area area ) {
        return Integer.compare( area.getElevation(), getElevation() );
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( "Area (" );
        builder.append( this.getElevation() );
        builder.append( " [" );
        for ( int i = 0; i < paths.size(); ++i ) {
            builder.append( paths.get( i ).getElevation() )
                   .append( i < paths.size() - 1 ? " " : "" );
        }
        builder.append( "])" );

        return builder.toString();
    }


    public static final class AreaBuilder {

        private int elevation = 0;
        private List<Area> paths = new ArrayList<>();


        public AreaBuilder() {}


        public AreaBuilder elevation( final int elevation ) {
            this.elevation = elevation;
            return this;
        }


        public AreaBuilder paths( final List<Area> paths ) {
            this.paths = paths;
            return this;
        }


        public Area build() {
            return new Area( this.elevation, this.paths );
        }
    }
}