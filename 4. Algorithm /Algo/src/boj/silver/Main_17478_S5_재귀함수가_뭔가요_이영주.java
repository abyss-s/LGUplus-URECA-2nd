package boj.silver;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_17478_S5_����Լ���_������_�̿��� {

	public static void main(String[] args) throws Exception {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		System.out.println("��� �� ��ǻ�Ͱ��а� �л��� ������ �������� ã�ư� ������.");
		recursice_func(N, "");
	}

	public static void recursice_func(int n, String prefix) {
		System.out.println(prefix + "\"����Լ��� ������?\"");

		// ���̰� ���� ���
		if (n == 0) {
			System.out.println(prefix + "\"����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����\"");
			System.out.println(prefix + "��� �亯�Ͽ���.");
			return;
		}

		System.out.println(prefix + "\"�� ����. �������� �� �� ����⿡ �̼��� ��� ������ ����� ������ �־���.");
		System.out.println(prefix + "���� ������� ��� �� ���ο��� ������ ������ �߰�, ��� �����Ӱ� ����� �־���.");
		System.out.println(prefix + "���� ���� ��κ� �ǾҴٰ� �ϳ�. �׷��� ��� ��, �� ���ο��� �� ���� ã�ƿͼ� ������.\"");

		recursice_func(n - 1, "____" + prefix);

		System.out.println(prefix + "��� �亯�Ͽ���.");
	}

	//	ù ��° ����: �Լ��� ���۵Ǹ� �л��� �����Կ��� "����Լ��� ������?"��� �����մϴ�.
	//
	//	��� �غ�: ���̿� ���� �ΰ��� �亯�� �����մϴ�!
	//
	//	��� ȣ���� ��ӵ� ���: �������� �̾߱⸦ �ϸ� �ٽ� ������ ����ϱ� ���� �ڱ� �ڽ��� ȣ���մϴ�.
	//	�⺻ ��� (N�� 0�� ��): "����Լ��� �ڱ� �ڽ��� ȣ���ϴ� �Լ����."��� ����� �ϰ� ��ȭ�� �������� �����ϴ�.
	// recursice_func(0)�� ���� �� recursice_func(1)���� ���ƿͼ�
	// "��� �亯�Ͽ���." ���
	// �ٽ� recursice_func(2)�� ���ƿͼ�
	// "��� �亯�Ͽ���." ���

}