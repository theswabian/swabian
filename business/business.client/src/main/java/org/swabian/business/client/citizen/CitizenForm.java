package org.swabian.business.client.citizen;

import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.dto.FormData.SdkCommand;
import org.eclipse.scout.rt.client.ui.desktop.bookmark.BookmarkFolderForm.MainBox.GroupBox.NameField;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCancelButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractOkButton;
import org.eclipse.scout.rt.client.ui.form.fields.datefield.AbstractDateField;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.imagefield.AbstractImageField;
import org.eclipse.scout.rt.client.ui.form.fields.radiobuttongroup.AbstractRadioButtonGroup;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.smartfield.AbstractSmartField;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.ObjectUtility;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.shared.services.common.code.ICodeType;
import org.eclipse.scout.rt.shared.services.lookup.ILookupCall;
import org.swabian.business.client.citizen.CitizenForm.ModifyDirtyHandler;
import org.swabian.business.client.citizen.CitizenForm.NewDirtyHandler;
import org.swabian.business.client.citizen.CitizenForm.MainBox.CancelButton;
import org.swabian.business.client.citizen.CitizenForm.MainBox.DetailsBox;
import org.swabian.business.client.citizen.CitizenForm.MainBox.GeneralBox;
import org.swabian.business.client.citizen.CitizenForm.MainBox.OkButton;
import org.swabian.business.client.citizen.CitizenForm.MainBox.DetailsBox.ContactInfoBox;
import org.swabian.business.client.citizen.CitizenForm.MainBox.DetailsBox.NotesBox;
import org.swabian.business.client.citizen.CitizenForm.MainBox.DetailsBox.WorkBox;
import org.swabian.business.client.citizen.CitizenForm.MainBox.DetailsBox.ContactInfoBox.AddressBox;
import org.swabian.business.client.citizen.CitizenForm.MainBox.DetailsBox.ContactInfoBox.EmailField;
import org.swabian.business.client.citizen.CitizenForm.MainBox.DetailsBox.ContactInfoBox.MobileField;
import org.swabian.business.client.citizen.CitizenForm.MainBox.DetailsBox.ContactInfoBox.PhoneField;
import org.swabian.business.client.citizen.CitizenForm.MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox;
import org.swabian.business.client.citizen.CitizenForm.MainBox.DetailsBox.ContactInfoBox.AddressBox.StreetField;
import org.swabian.business.client.citizen.CitizenForm.MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox.CityField;
import org.swabian.business.client.citizen.CitizenForm.MainBox.DetailsBox.ContactInfoBox.AddressBox.LocationBox.CountryField;
import org.swabian.business.client.citizen.CitizenForm.MainBox.DetailsBox.NotesBox.NotesField;
import org.swabian.business.client.citizen.CitizenForm.MainBox.DetailsBox.WorkBox.EmailWorkField;
import org.swabian.business.client.citizen.CitizenForm.MainBox.DetailsBox.WorkBox.OrganizationField;
import org.swabian.business.client.citizen.CitizenForm.MainBox.DetailsBox.WorkBox.PhoneWorkField;
import org.swabian.business.client.citizen.CitizenForm.MainBox.DetailsBox.WorkBox.PositionField;
import org.swabian.business.client.citizen.CitizenForm.MainBox.GeneralBox.DateOfBirthField;
import org.swabian.business.client.citizen.CitizenForm.MainBox.GeneralBox.GenderGroup;
import org.swabian.business.client.citizen.CitizenForm.MainBox.GeneralBox.HandleField;
import org.swabian.business.client.citizen.CitizenForm.MainBox.GeneralBox.MonikerField;
import org.swabian.business.client.citizen.CitizenForm.MainBox.GeneralBox.PictureField;
import org.swabian.business.client.common.AbstractDirtyFormHandler;
import org.swabian.business.client.common.CountryLookupCall;
import org.swabian.business.shared.citizen.CitizenFormData;
import org.swabian.business.shared.citizen.GenderCodeType;
import org.swabian.business.shared.citizen.ICitizenService;
import org.swabian.business.shared.citizen.UpdateCitizenPermission;

@FormData(value = CitizenFormData.class, sdkCommand = SdkCommand.CREATE) // <1>
public class CitizenForm extends AbstractForm {

	private UUID citizenId;

	@FormData
	public UUID getCitizenId() {
		return citizenId;
	}

	@FormData
	public void setCitizenId(UUID citizenId) {
		this.citizenId = citizenId;
	}

	@Override
	public Object computeExclusiveKey() { // <3>
		return getCitizenId();
	}

	@Override
	protected int getConfiguredDisplayHint() { // <4>
		return IForm.DISPLAY_HINT_VIEW;
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Person");
	}

	public void startModify() {
		startInternalExclusive(new ModifyDirtyHandler());
	}

	public void startNew() {
		startInternal(new NewDirtyHandler());
	}

	public AddressBox getAddressBox() {
		return getFieldByClass(AddressBox.class);
	}

	public CancelButton getCancelButton() {
		return getFieldByClass(CancelButton.class);
	}

	public NotesBox getNotesBox() {
		return getFieldByClass(NotesBox.class);
	}

	public NotesField getNotesField() {
		return getFieldByClass(NotesField.class);
	}

	public OrganizationField getOrganizationField() {
		return getFieldByClass(OrganizationField.class);
	}

	public ContactInfoBox getPersonDetailsBox() {
		return getFieldByClass(ContactInfoBox.class);
	}

	public DateOfBirthField getDateOfBirthField() {
		return getFieldByClass(DateOfBirthField.class);
	}

	public DetailsBox getDetailsBox() {
		return getFieldByClass(DetailsBox.class);
	}

	public EmailField getEmailField() {
		return getFieldByClass(EmailField.class);
	}

	public EmailWorkField getEmailWorkField() {
		return getFieldByClass(EmailWorkField.class);
	}

	public HandleField getHandleField() {
		return getFieldByClass(HandleField.class);
	}

	public GenderGroup getGenderGroup() {
		return getFieldByClass(GenderGroup.class);
	}

	public GeneralBox getGeneralBox() {
		return getFieldByClass(GeneralBox.class);
	}

	public MonikerField getMonikerField() {
		return getFieldByClass(MonikerField.class);
	}

	public MainBox getMainBox() {
		return getFieldByClass(MainBox.class);
	}

	public MobileField getMobileField() {
		return getFieldByClass(MobileField.class);
	}

	public OkButton getOkButton() {
		return getFieldByClass(OkButton.class);
	}

	public PhoneField getPhoneField() {
		return getFieldByClass(PhoneField.class);
	}

	public PhoneWorkField getPhoneWorkField() {
		return getFieldByClass(PhoneWorkField.class);
	}

	public PictureField getPictureField() {
		return getFieldByClass(PictureField.class);
	}

	public PositionField getPositionField() {
		return getFieldByClass(PositionField.class);
	}

	public WorkBox getWorkBox() {
		return getFieldByClass(WorkBox.class);
	}

	@Order(10)
	@ClassId("27a040ac-eac5-47c6-a826-572633b9d4ef")
	public class MainBox extends AbstractGroupBox { // <1>

		@Order(10)
		@ClassId("08832a97-8845-4ff4-8dfd-c29366c22742")
		public class GeneralBox extends AbstractGroupBox { // <2>

			@Override
			protected String getConfiguredLabel() {
				return TEXTS.get("General");
			}

			@Override
			protected double getConfiguredGridWeightY() {
				// do not allow the general box to grow or shrink vertically.
				return 0;
			}

			@Override
			protected boolean getConfiguredLabelVisible() {
				return false;
			}

			@Order(20)
			@ClassId("6366a23e-f8ba-4b50-b814-202e63daffc8")
			public class PictureField extends AbstractImageField {

				@Override // <2>
				protected Class<NameField> getConfiguredMasterField() {
					return NameField.class;
				}

				@Override // <3>
				protected void execChangedMasterValue(Object newMasterValue) {
					updateImage((String) newMasterValue);
				}

				@Override
				protected boolean getConfiguredLabelVisible() {
					return false;
				}

				@Override
				protected int getConfiguredGridH() {
					return 5;
				}

				@Override
				protected boolean getConfiguredAutoFit() {
					return true;
				}

				protected void updateImage(String handle) {
					setImageUrl("https://robertsspaceindustries.com/citizens/" + handle);
				}
			}
			// end::pictureField[]

			// tag::nameFields[]
			@Order(30)
			@ClassId("359be835-439f-456e-9b0d-c832b034a298")
			public class HandleField extends AbstractStringField {

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Handle");
				}
			}

			@Order(40)
			@ClassId("8679ade5-21fb-470e-8f00-13bd15199101")
			public class MonikerField extends AbstractStringField {

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("CommunityMoniker");
				}
			}
			// end::nameFields[]
			// tag::dateOfBirthField[]

			@Order(50)
			@ClassId("7c602360-9daa-44b8-abb6-94ccf9b9db59")
			public class DateOfBirthField extends AbstractDateField {

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("DateOfBirth");
				}
				// end::dateOfBirthField[]

				@Override
				protected Date execValidateValue(Date rawValue) {
					if (ObjectUtility.compareTo(rawValue, new Date()) > 0) {
						throw new VetoException(TEXTS.get("DateOfBirthCanNotBeInFuture"));
					}

					return super.execValidateValue(rawValue);
				}
				// tag::dateOfBirthField[]
			}
			// end::dateOfBirthField[]
			// tag::genderField[]

			@Order(60)
			@ClassId("b9d0593e-3938-4f97-bdca-fdb6a1ce1d77")
			public class GenderGroup extends AbstractRadioButtonGroup<String> {

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Gender");
				}

				@Override // <1>
				protected Class<? extends ICodeType<?, String>> getConfiguredCodeType() {
					return GenderCodeType.class;
				}
			}
			// end::genderField[]
			// tag::layout[]
		}

		@Order(20)
		@ClassId("3469046e-ee95-4e86-b0c9-a8ed01fbf664")
		public class DetailsBox extends AbstractTabBox { // <3>

			@Order(10)
			@ClassId("2081b483-3d6e-4239-b7da-b6e2d2aa3b7a")
			public class ContactInfoBox extends AbstractGroupBox { // <4>

				// end::layout[]
				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("ContactInfo");
				}

				// tag::layout[]
				// tag::addressBox[]
				// tag::validateAddress[]
				@Order(10)
				@ClassId("736450dd-ba89-43cd-ba52-bcd31196b462")
				public class AddressBox extends AbstractGroupBox {
					// end::layout[]
					// end::validateAddress[]

					@Override
					protected boolean getConfiguredBorderVisible() {
						return false;
					}

					@Override
					protected int getConfiguredGridH() { // <1>
						return 3;
					}

					@Override
					protected int getConfiguredGridW() { // <1>
						return 1;
					}

					@Override
					protected int getConfiguredGridColumnCount() { // <2>
						return 1;
					}
					// end::addressBox[]

					public StreetField getStreetField() {
						return getFieldByClass(StreetField.class);
					}

					public LocationBox getLocationBox() {
						return getFieldByClass(LocationBox.class);
					}

					public CityField getCityField() {
						return getFieldByClass(CityField.class);
					}

					public CountryField getCountryField() {
						return getFieldByClass(CountryField.class);
					}

					@Order(10)
					@ClassId("a9137ad1-af9d-4fef-a69d-3e3d9ce48f21")
					public class StreetField extends AbstractStringField {
						// end::validateAddress[]

						@Override
						protected String getConfiguredLabel() {
							return TEXTS.get("Street");
						}
						// end::addressBox[]
						// tag::validateAddress[]

						@Override // <1>
						protected void execChangedValue() {
							validateAddressFields(); // <2>
						}
						// tag::addressBox[]
					}

					// end::validateAddress[]
					// use a sequence box for horizontal layout // <3>
					// tag::validateAddress[]
					@Order(20)
					@ClassId("a278333c-057e-4c1d-a442-0c1dd62fdca7")
					public class LocationBox extends AbstractSequenceBox {
						// end::validateAddress[]

						@Override
						protected String getConfiguredLabel() {
							return TEXTS.get("Location");
						}

						@Override
						protected boolean getConfiguredAutoCheckFromTo() { // <4>
							return false;
						}
						// tag::validateAddress[]

						@Order(10)
						@ClassId("3ea6ac2a-976e-4c7f-b04b-ec0d7d1ae5ec")
						public class CityField extends AbstractStringField {
							// end::validateAddress[]

							@Override
							protected String getConfiguredLabel() {
								return TEXTS.get("City");
							}

							@Override
							protected byte getConfiguredLabelPosition() {
								return LABEL_POSITION_ON_FIELD; // <5>
							}
							// end::addressBox[]
							// tag::validateAddress[]

							@Override
							protected void execChangedValue() {
								validateAddressFields(); // <2>
							}
							// tag::addressBox[]
						}

						@Order(20)
						@ClassId("d4dfce4f-019b-4a61-ba78-347ef67cf80f")
						public class CountryField extends AbstractSmartField<String> {
							// end::validateAddress[]

							@Override
							protected String getConfiguredLabel() {
								return TEXTS.get("Country");
							}
							// end::addressBox[]
							// tag::validateAddress[]

							@Override
							protected void execChangedValue() {
								validateAddressFields(); // <2>
							}
							// tag::addressBox[]
							// end::validateAddress[]

							@Override
							protected byte getConfiguredLabelPosition() {
								return LABEL_POSITION_ON_FIELD;
							}

							@Override
							protected Class<? extends ILookupCall<String>> getConfiguredLookupCall() {
								return CountryLookupCall.class;
							}
							// tag::validateAddress[]
						}
					}

					protected void validateAddressFields() {
						boolean hasStreet = StringUtility.hasText(getStreetField().getValue());
						boolean hasCity = StringUtility.hasText(getCityField().getValue());

						getCityField().setMandatory(hasStreet); // <3>
						getCountryField().setMandatory(hasStreet || hasCity);
					}
				}

				@Order(20)
				@ClassId("136a3c0c-91bf-427c-8020-507bfd391098")
				public class PhoneField extends AbstractStringField {

					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Phone");
					}
				}

				@Order(30)
				@ClassId("7dc64c60-5713-4376-a3e0-41c0a8e2b503")
				public class MobileField extends AbstractStringField {

					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Mobile");
					}
				}

				// tag::email[]
				@Order(40)
				@ClassId("5f9d9363-8e57-4151-b281-7d401e64702c")
				public class EmailField extends AbstractStringField {

					// end::email[]
					// http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
					// tag::email[]
					private static final String EMAIL_PATTERN = // <1>
							"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
									+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Email");
					}

					@Override // <2>
					protected int getConfiguredMaxLength() {
						return 64;
					}

					@Override // <3>
					protected String execValidateValue(String rawValue) {
						if (rawValue != null && !Pattern.matches(EMAIL_PATTERN, rawValue)) {
							throw new VetoException(TEXTS.get("BadEmailAddress")); // <4>
						}

						return rawValue; // <5>
					}
				}
				// end::email[]
				// tag::layout[]
			}

			// tag::organizationField[]
			@Order(20)
			@ClassId("8e18a673-aca5-44a2-898f-60a744e4467a")
			public class WorkBox extends AbstractGroupBox {
				// end::layout[]
				// end::organizationField[]

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Work");
				}

				@Order(10)
				@ClassId("ee22af3c-b0a9-47a5-8931-d48a219d16b2")
				public class PositionField extends AbstractStringField {

					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Position");
					}
				}
				// tag::organizationField[]

				@Order(20)
				@ClassId("cd4a7afd-e0ac-4c79-bf2e-819aa491db27")
				public class OrganizationField extends AbstractSmartField<String> { // <1>

					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Organization");
					}

				}

				@Order(30)
				@ClassId("334720b0-75fa-400a-8305-983a7aa98549")
				public class PhoneWorkField extends AbstractStringField {

					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Phone");
					}
				}

				@Order(40)
				@ClassId("7f693443-ec4e-47fb-874e-b31328cc22fb")
				public class EmailWorkField extends AbstractStringField {

					@Override
					protected String getConfiguredLabel() {
						return TEXTS.get("Email");
					}
				}
				// tag::layout[]
				// tag::organizationField[]
			}
			// end::organizationField[]

			// tag::notes[]
			@Order(30)
			@ClassId("fcb5b155-2c89-4ef8-9a96-ac41e9032107")
			public class NotesBox extends AbstractGroupBox {
				// end::layout[]

				@Override
				protected String getConfiguredLabel() {
					return TEXTS.get("Notes");
				}

				@Order(10)
				@ClassId("ce791f14-fca6-4f11-8476-89cbf905eb2e")
				public class NotesField extends AbstractStringField {

					@Override
					protected int getConfiguredGridH() {
						return 4;
					}

					@Override
					protected boolean getConfiguredLabelVisible() {
						return false;
					}

					@Override
					protected boolean getConfiguredMultilineText() {
						return true;
					}
				}
				// tag::layout[]
			}
		}
		// end::notes[]

		@Order(30)
		@ClassId("e54548b8-601e-41a4-842c-db25b5f1cad1")
		public class OkButton extends AbstractOkButton {
		}

		@Order(40)
		@ClassId("26612eb9-1832-4284-ac5a-9f450dc7ff9b")
		public class CancelButton extends AbstractCancelButton {
		}
	}
	// end::layout[]

	// These classes (ModifyHandler and NewHandler) are only for documentation, they
	// are not used in the application production:
	// tag::handler[]
	public class ModifyHandler extends AbstractFormHandler {

		@Override
		protected void execLoad() {
			ICitizenService service = BEANS.get(ICitizenService.class); // <1>
			CitizenFormData formData = new CitizenFormData();
			exportFormData(formData); // <2>
			formData = service.load(formData); // <3>
			importFormData(formData); // <4>
			setEnabledPermission(new UpdateCitizenPermission());
			getForm().setSubTitle(calculateSubTitle()); // <5>
		}

		@Override
		protected void execStore() {
			ICitizenService service = BEANS.get(ICitizenService.class);
			CitizenFormData formData = new CitizenFormData();
			exportFormData(formData);
			service.store(formData); // <6>
		}
	}

	public class NewHandler extends AbstractFormHandler {

		@Override
		protected void execStore() {
			ICitizenService service = BEANS.get(ICitizenService.class);
			CitizenFormData formData = new CitizenFormData();
			exportFormData(formData);
			formData = service.create(formData); // <7>
			importFormData(formData);
		}
	}
	// end::handler[]

	// This modify handler is used in the application:
	public class ModifyDirtyHandler extends AbstractDirtyFormHandler {

		@Override
		protected void execLoad() {
			ICitizenService service = BEANS.get(ICitizenService.class);
			CitizenFormData formData = new CitizenFormData();
			exportFormData(formData);
			formData = service.load(formData);
			importFormData(formData);

			getForm().setSubTitle(calculateSubTitle());
		}

		@Override
		protected void execStore() {
			ICitizenService service = BEANS.get(ICitizenService.class);
			CitizenFormData formData = new CitizenFormData();
			exportFormData(formData);
			service.store(formData);
		}

		@Override
		protected void execDirtyStatusChanged(boolean dirty) {
			getForm().setSubTitle(calculateSubTitle());
		}

		@Override
		protected boolean getConfiguredOpenExclusive() {
			return true;
		}
	}

	// This new handler is used in the application:
	public class NewDirtyHandler extends AbstractDirtyFormHandler {

		@Override
		protected void execStore() {
			ICitizenService service = BEANS.get(ICitizenService.class);
			CitizenFormData formData = new CitizenFormData();
			exportFormData(formData);
			formData = service.create(formData);
			importFormData(formData);
		}

		@Override
		protected void execDirtyStatusChanged(boolean dirty) {
			getForm().setSubTitle(calculateSubTitle());
		}
	}

	@Override // <1>
	protected boolean execValidate() {
		boolean noFirstName = StringUtility.isNullOrEmpty(getHandleField().getValue());
		boolean noLastName = StringUtility.isNullOrEmpty(getMonikerField().getValue());

		if (noFirstName && noLastName) {
			getHandleField().requestFocus(); // <2>

			throw new VetoException(TEXTS.get("MissingName")); // <3>
		}

		return true;
	}

	protected String calculateSubTitle() {
		return StringUtility.join(" ", getHandleField().getValue(), getMonikerField().getValue());
	}
}
