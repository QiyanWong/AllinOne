class Cell {
  int n;
  int m;
  int value;
  public Cell(int n, int m, int value){
    this.n = n;
    this.m = m;
    this.value = value;
  }
  @Override
  public boolean equals(Object o){
    if(this == o) return true;
    if(o instanceof Cell){
      Cell c = (Cell)o;
      if(c.n == this.n && c.m == this.m) return true;
      else return false;
    }
    return false;
  }
}