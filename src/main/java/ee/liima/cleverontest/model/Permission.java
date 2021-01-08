package ee.liima.cleverontest.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ee.liima.cleverontest.serialize.CustomPermissionSerializer;
import ee.liima.cleverontest.serialize.CustomPermissionsListSerializer;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    @GeneratedValue
    @Column(name = "id", unique=true)
    private Long id;

    @NonNull
    @NotNull
    @Size(min = 3, message = "Name can't be smaller than 3 characters!")
    @Column(name = "permission_name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonSerialize(using = CustomPermissionSerializer.class)
    private Permission parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonSerialize(using = CustomPermissionsListSerializer.class)
    private List<Permission> subPermissions;

}
