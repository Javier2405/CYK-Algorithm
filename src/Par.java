
public class Par {
	
	private Generador gen_v1,
					gen_v2;
	private V v1,
				v2;
	
	public Par(Generador gen1, Generador gen2, V v1, V v2) {
		this.gen_v1 = gen1;
		this.gen_v2 = gen2;
		this.v1 = v1;
		this.v2 = v2;
	}
	
	public Generador getGen1() {
		return this.gen_v1;
	}
	
	public Generador getGen2() {
		return this.gen_v2;
	}
	
	public V getV1() {
		return this.v1;
	}
	
	public V getV2() {
		return this.v2;
	}
}
