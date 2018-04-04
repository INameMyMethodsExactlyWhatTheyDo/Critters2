package assignment5;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class Grid {
	private int cols;
	private int rows;
	public GridPane gridP;
	//private int unitSize = 10;
	public Grid() {
    	gridP = new GridPane();
    	gridP.setGridLinesVisible(true);
    	cols = Params.world_width;
    	rows = Params.world_height;
    	
    	for (int i = 0; i < Params.world_height; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / Params.world_height);
            gridP.getColumnConstraints().add(colConst);
        }
        for (int i = 0; i < Params.world_width; i++) {
            RowConstraints rowConst = new RowConstraints();
            rowConst.setPercentHeight(100.0 / Params.world_width);
            gridP.getRowConstraints().add(rowConst);         
        }
	}

}
