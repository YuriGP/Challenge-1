import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;


public class Mountain {

    private static final List<Coordinates> COORDINATES = Arrays.asList( Coordinates.values() );

    final private List<Area> areas;


    public Mountain( final Area[][] map ) {
        if ( map == null ) {
            throw new IllegalArgumentException( "Should be a valid map" );
        }

        int xLength = map[0].length;
        int yLength = map.length;

        this.areas = new ArrayList<>( xLength * yLength );

        for ( int i = 0; i < map.length; ++i ) {
            for ( int j = 0; j < map[i].length; ++j ) {

                for ( Coordinates c : COORDINATES ) {
                    int neighborX = c.getX( i );
                    int neighborY = c.getY( j );

                    if ( neighborX >= 0
                         && neighborX < xLength
                         && neighborY >= 0
                         && neighborY < yLength
                         && map[neighborX][neighborY].getElevation() < map[i][j].getElevation() ) {

                        map[i][j].addPath( map[neighborX][neighborY] );
                    }
                }

                this.areas.add( map[i][j] );
            }
        }
    }


    private void findBestSlope( final Area head ) {
        if ( head == null ) {
            throw new IllegalArgumentException( "Should be a valid Area" );
        }

        Stack<Area> stack = new Stack<>();
        Set<Area> visited = new HashSet<>();

        stack.push( head );

        while ( !stack.isEmpty() ) {
            Area area = stack.pop();

            if ( area.getPaths().size() == 0 ) {
                List<Area> path = new ArrayList<>();
                path.add( area );

                area.setBestSlope( Slope.builder()
                                        .path( path )
                                        .build() );
                continue;
            }

            if ( visited.contains( area ) ) {
                if ( area.getBestSlope() == null ) {
                    Slope bestSlope = null;
                    for ( Area path : area.getPaths() ) {
                        Slope pathSlope = path.getBestSlope();

                        if ( pathSlope.compareTo( bestSlope ) > 0 ) {
                            bestSlope = pathSlope;
                        }
                    }

                    List<Area> path = new ArrayList<>( bestSlope.getPath() );
                    path.add( area );

                    area.setBestSlope( Slope.builder()
                                            .path( path )
                                            .build() );
                }
            } else {
                visited.add( area );
                stack.push( area );
                for ( Area b : area.getPaths() ) {
                    stack.push( b );
                }
            }
        }
    }


    public Slope findBestSlope() {
        Slope bestSlope = null;
        for ( Area area : areas ) {
            if ( area.getBestSlope() == null ) {
                findBestSlope( area );
            }

            if ( area.getBestSlope().compareTo( bestSlope ) > 0 ) {
                bestSlope = area.getBestSlope();
            }
        }

        return bestSlope;
    }


    private enum Coordinates {

        NORTH {
            @Override
            public int getX( final int currentX ) {
                return currentX;
            }


            @Override
            public int getY( final int currentY ) {
                return currentY - 1;
            }
        },
        EAST {
            @Override
            public int getX( final int currentX ) {
                return currentX + 1;
            }


            @Override
            public int getY( final int currentY ) {
                return currentY;
            }
        },
        SOUTH {
            @Override
            public int getX( final int currentX ) {
                return currentX;
            }


            @Override
            public int getY( final int currentY ) {
                return currentY + 1;
            }
        },
        WEST {
            @Override
            public int getX( final int currentX ) {
                return currentX - 1;
            }


            @Override
            public int getY( final int currentY ) {
                return currentY;
            }
        };


        public abstract int getX( final int currentX );

        public abstract int getY( final int currentY );
    }
}
