package Signal;

public class SignalWindow {

	public enum WindowType  { RECTANGULAR, BARTLETT, HANNING, HAMMING, BLACKMAN };

	WindowType windowType = WindowType.HAMMING; // defaults to hamming window

	public SignalWindow() {}
	public void setWindowType(WindowType wt) {	windowType = wt; }
	public WindowType getWindowType() { return windowType;	}

	public double[] generate(WindowType windowType, int nSamples) {
		this.windowType=windowType;
		// generate nSamples window function values
		// for index values 0 .. nSamples - 1
		int m = nSamples / 2;
		double r;
		double pi = Math.PI;
		double[] w = new double[nSamples];
		switch (windowType) {
		case BARTLETT: // Bartlett (triangular) window
			for (int n = 0; n < nSamples; n++)
				w[n] = 1.0f - Math.abs(n - m) / m;
			break;
		case HANNING: // Hanning window
			r = pi / (m + 1);
			for (int n = -m; n < m; n++)
				w[m + n] = 0.5f + 0.5f * Math.cos(n * r);
			break;
		case HAMMING: // Hamming window
			r = pi / m;
			for (int n = -m; n < m; n++)
				w[m + n] = 0.54f + 0.46f * Math.cos(n * r);
			break;
		case BLACKMAN: // Blackman window
			r = pi / m;
			for (int n = -m; n < m; n++)
				w[m + n] = 0.42f + 0.5f * Math.cos(n * r) + 0.08f
				* Math.cos(2 * n * r);
			break;
		default: // Rectangular window function
			for (int n = 0; n < nSamples; n++)
				w[n] = 1.0f;
		}
		return w;
	}
}
