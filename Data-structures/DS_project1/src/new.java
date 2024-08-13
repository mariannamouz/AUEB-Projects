    public List<Point> rangeSearch(Rectangle rectangle){
        List<Point> pointList = new List<Point>();
        //Empty tree
        if(this.isEmpty()){
            return pointList;
        }
        return this.rangeSearch_HelperFunction(pointList,rectangle,new Rectangle(0,100,0,100),true);
    }

    /**
     * Helper function to find the points contained in a given rectangle
     * @param pointList a queue in which we save the nodes that are in the given rectangle
     * @param rectangle the rectangle in which we are searching
     * @param pointRectangle the rectangle representation of a node
     * @param first boolean to check if we are in x or y axis
     * @return the List<Point> containing the points found in the rectangle
     */
    private List<Point> rangeSearch_HelperFunction(List<Point> pointList, Rectangle rectangle,Rectangle pointRectangle, boolean first){
        //Empty branch case
        if(this.isEmpty()){
            return pointList;
        }

        //Checking if this node is contained in the rectangle
        if(rectangle.contains(this.getHead().getData())){
            pointList.put(this.getHead().getData());
        }

        //If the rectangle representation of a node does not intersects the rectangle in which we
        //are looking for then we don't search the subtrees
        if(!(rectangle.intersects(pointRectangle))){
            return pointList;
        }
        else{
            //x case
            if(first){
                getLeftSubTree(head).rangeSearch_HelperFunction(pointList,rectangle,new Rectangle(pointRectangle.getMinx(),head.getData().getX(),pointRectangle.getMiny(),pointRectangle.getMaxy()),false);
                getRightSubTree(head).rangeSearch_HelperFunction(pointList,rectangle,new Rectangle(head.getData().getX(),pointRectangle.getMaxx(),pointRectangle.getMiny(),pointRectangle.getMaxy()),false);
            }
            //y case
            else{
                getLeftSubTree(head).rangeSearch_HelperFunction(pointList,rectangle,new Rectangle(pointRectangle.getMinx(),pointRectangle.getMaxx(),pointRectangle.getMiny(),head.getData().getY()),true);
                getRightSubTree(head).rangeSearch_HelperFunction(pointList,rectangle,new Rectangle(pointRectangle.getMinx(), pointRectangle.getMaxx(), head.getData().getY(), pointRectangle.getMaxy()),true);
            }
        }
        return pointList;
    }