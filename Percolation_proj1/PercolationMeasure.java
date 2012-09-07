class PercolationMeasure {
    
    public static void main(String[] args) {
        
        String[] params = new String[2];
        params[0] = "5";
        params[1] = "10";
        
        Stopwatch stopwatch = new Stopwatch();
        double startTime = 0;
        double elapsedTime = 0;
        while(Integer.parseInt(params[0]) < 170) {
            startTime = stopwatch.elapsedTime();
            PercolationStats.main(params);
            elapsedTime = stopwatch.elapsedTime() - startTime;
            System.out.println("Problem size (N): " + params[0]);
            System.out.println("Running time (in minutes): " + elapsedTime/60.0);
            System.out.println();
            params[0] = Integer.toString((2*Integer.parseInt(params[0])));
        }
        
       
    }
    
}