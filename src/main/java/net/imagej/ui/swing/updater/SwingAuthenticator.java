

package net.imagej.ui.swing.updater;

import java.io.UnsupportedEncodingException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;


public class SwingAuthenticator extends Authenticator {

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		final JPanel panel = new JPanel();
		panel.setLayout(new MigLayout("wrap 2"));

		panel.add(new JLabel(getRequestingHost() + " asks for authentication:"), "span 2");
		panel.add(new JLabel("    " + getRequestingPrompt()), "span 2");

		panel.add(new JLabel("User:"));
		final JTextField user = new JTextField(20);
		panel.add(user);

		panel.add(new JLabel("Password:"));
		final JPasswordField password = new JPasswordField(20);
		panel.add(password);

		if (JOptionPane.showConfirmDialog(null, panel, getRequestingPrompt(),
			JOptionPane.OK_CANCEL_OPTION) != JOptionPane.OK_OPTION) return null;
		// work around Java's internal ISO-8859-1 encoding
		final String string = new String(password.getPassword());
		final byte[] bytes;
		try {
			bytes = string.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		final char[] chars = new char[bytes.length];
		for (int i = 0; i < bytes.length; i++) chars[i] = (char)(bytes[i] & 0xff);
		return new PasswordAuthentication(user.getText(), chars);
	}

}
