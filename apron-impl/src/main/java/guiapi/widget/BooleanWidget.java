package guiapi.widget;

import de.matthiasmann.twl.Button;
import de.matthiasmann.twl.model.SimpleButtonModel;
import guiapi.ModScreen;
import guiapi.ModSettingScreen;
import guiapi.setting.BooleanSetting;

public class BooleanWidget extends SettingWidget implements Runnable {
	public Button button;
	public String falseText;
	public BooleanSetting settingReference;
	public String trueText;

	public BooleanWidget(BooleanSetting setting, String title) {
		this(setting, title, "true", "false");
	}

	public BooleanWidget(BooleanSetting setting, String title, String trueText, String falseText) {
		super(title);
		this.setTheme("");
		this.trueText = trueText;
		this.falseText = falseText;
		SimpleButtonModel buttonModel = new SimpleButtonModel();
		this.button = new Button(buttonModel);
		buttonModel.addActionCallback(this);
		this.add(this.button);
		this.settingReference = setting;
		this.settingReference.displayWidget = this;
		this.update();
	}

	@Override
	public void addCallback(Runnable paramRunnable) {
		this.button.getModel().addActionCallback(paramRunnable);
	}

	@Override
	public void removeCallback(Runnable paramRunnable) {
		this.button.getModel().removeActionCallback(paramRunnable);
	}

	@Override
	public void run() {
		if (this.settingReference != null) {
			this.settingReference.set(!this.settingReference.get(ModSettingScreen.guiContext), ModSettingScreen.guiContext);
		}

		this.update();
		ModScreen.clicksound();
	}

	@Override
	public void update() {
		this.button.setText(this.userString());
	}

	@Override
	public String userString() {
		if (this.settingReference != null) {
			if (this.niceName.length() > 0) {
				return String.format("%s: %s", this.niceName, this.settingReference.get(ModSettingScreen.guiContext) ? this.trueText : this.falseText);
			} else {
				return this.settingReference.get(ModSettingScreen.guiContext) ? this.trueText : this.falseText;
			}
		} else {
			return this.niceName.length() > 0 ? String.format("%s: %s", this.niceName, "no value") : "no value or title";
		}
	}
}
